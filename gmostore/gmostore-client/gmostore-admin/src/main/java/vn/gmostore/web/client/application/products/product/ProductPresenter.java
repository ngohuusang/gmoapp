package vn.gmostore.web.client.application.products.product;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.web.client.application.event.ActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent.ActionType;
import vn.gmostore.web.client.application.event.DisplayMessageEvent;
import vn.gmostore.web.client.application.event.GoBackEvent;
import vn.gmostore.web.client.application.products.event.ProductAddedEvent;
import vn.gmostore.web.client.application.products.product.navigation.NavigationTab;
import vn.gmostore.web.client.application.products.product.navigation.NavigationTabEvent;
import vn.gmostore.web.client.application.widget.message.Message;
import vn.gmostore.web.client.application.widget.message.MessageStyle;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.resources.ProductMessages;
import vn.gmostore.web.client.rest.ProductService;
import vn.gmostore.web.client.util.ErrorHandlerAsyncCallback;

import com.google.gwt.user.client.Window;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.NoResult;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ProductPresenter extends Presenter<ProductPresenter.MyView, ProductPresenter.MyProxy> implements ProductUiHandlers, NavigationTab,
        GoBackEvent.GoBackHandler, ActionBarEvent.ActionBarHandler {

    public interface MyView extends View, HasUiHandlers<ProductUiHandlers> {
        void edit(ProductDetailDto product);

        void resetFields(ProductDetailDto product);

        void getProduct();
    }

    public interface MyProxy extends ProxyPlace<ProductPresenter> {

    }

    private final ProductService productService;
    private final ProductMessages messages;
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;
    private final ProductProxyFactory productProxyFactory;

    private ProductDetailDto product;

    @Inject
    public ProductPresenter(EventBus eventBus, MyView view, DispatchAsync dispatcher, ProductService productService, PlaceManager placeManager,
            ProductProxyFactory productProxyFactory, ProductMessages messages, @Assisted
            MyProxy proxy, @Assisted
            ProductDetailDto product) {
        super(eventBus, view, proxy);

        this.dispatcher = dispatcher;
        this.productService = productService;
        this.messages = messages;
        this.placeManager = placeManager;
        this.productProxyFactory = productProxyFactory;
        this.product = product != null ? product : new ProductDetailDto();

        getView().setUiHandlers(this);
    }

    @Override
    public void onGoBack(GoBackEvent event) {
        placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.getProducts()).build());
    }

    @Override
    public void onActionEvent(ActionBarEvent event) {
        if (event.isTheSameToken(NameTokens.newProduct)) {
            if (event.getActionType() == ActionType.DONE) {
                getView().getProduct();
            }
        } else if (event.isTheSameToken(product.getId() + product.getFullName())) {
            if (event.getActionType() == ActionType.UPDATE) {
                getView().getProduct();
            } else if (event.getActionType() == ActionType.DELETE) {
                onDeleteProduct();
            }
        }
    }

    @Override
    public void onCancel() {
        NavigationTabEvent.fireClose(this, this);
    }

    @Override
    public void onSave(final ProductDetailDto product) {
        dispatcher.execute(productService.saveOrCreate(product), new ErrorHandlerAsyncCallback<GetResult<ProductDetailDto>>(this) {
            @Override
            public void onSuccess(GetResult<ProductDetailDto> result) {
                onProductSaved(product, result.getResult());
            }
        });
    }

    @Override
    public String getName() {
        if (product.getId() != null) {
            return product.getFullName() + " " + product.getCurrentVersion();
        } else {
            return "New product";
        }
    }

    @Override
    public String getToken() {
        return getProxy().getNameToken();
    }

    @Override
    public boolean isClosable() {
        return true;
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(GoBackEvent.getType(), this);
        addRegisteredHandler(ActionBarEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        //        dispatcher.execute(manufacturerService.getManufacturers(), new AbstractAsyncCallback<GetResults<ManufacturerDto>>() {
        //            @Override
        //            public void onSuccess(GetResults<ManufacturerDto> result) {
        //                onGetManufacturerSuccess(result.getResults());
        //            }
        //        });

        Boolean createNew = placeManager.getCurrentPlaceRequest().matchesNameToken(NameTokens.newProduct);
        List<ActionType> actions;
        if (createNew) {
            actions = Arrays.asList(ActionType.DONE);
            ChangeActionBarEvent.fire(this, actions, false);
        } else {
            actions = Arrays.asList(ActionType.DELETE, ActionType.UPDATE);
            ChangeActionBarEvent.fire(this, actions, false);
        }

        NavigationTabEvent.fireReveal(this, this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, RootProductPresenter.TYPE_SetProductContent, this);
    }

    //    private void onGetManufacturerSuccess(List<ManufacturerDto> manufacturerDtos) {
    //        getView().setAllowedManufacturers(manufacturerDtos);
    //        getView().edit(product);
    //    }

    private void onProductSaved(ProductDetailDto oldProduct, ProductDetailDto newProduct) {
        DisplayMessageEvent.fire(ProductPresenter.this, new Message(messages.productSaved(), MessageStyle.SUCCESS));
        ProductAddedEvent.fire(ProductPresenter.this, newProduct, oldProduct.getId() == null);

        if (oldProduct.getId() == null) {
            product = new ProductDetailDto();
            getView().resetFields(product);

            MyProxy proxy = productProxyFactory.create(newProduct, newProduct.getId() + newProduct.getFullName());

            placeManager.revealPlace(new PlaceRequest.Builder().nameToken(proxy.getNameToken()).build());
        }
    }

    private void onDeleteProduct() {
        Boolean confirm = Window.confirm("Are you sure you want to delete " + product.getFullName() + "?");
        if (confirm) {
            dispatcher.execute(productService.delete(product.getId()), new ErrorHandlerAsyncCallback<NoResult>(this) {
                @Override
                public void onSuccess(NoResult noResult) {
                    NavigationTabEvent.fireClose(ProductPresenter.this, ProductPresenter.this);
                }
            });
        }
    }
}
