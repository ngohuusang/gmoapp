package vn.gmostore.web.client.application.products;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.NumberDto;
import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.web.client.application.ApplicationPresenter;
import vn.gmostore.web.client.application.event.ActionBarEvent;
import vn.gmostore.web.client.application.event.ActionBarVisibilityEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent.ActionType;
import vn.gmostore.web.client.application.products.ProductsPresenter.MyProxy;
import vn.gmostore.web.client.application.products.ProductsPresenter.MyView;
import vn.gmostore.web.client.application.products.event.ProductAddedEvent;
import vn.gmostore.web.client.application.products.product.ProductProxyFactory;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.rest.ProductService;
import vn.gmostore.web.client.security.LoggedInGatekeeper;
import vn.gmostore.web.client.util.AbstractAsyncCallback;
import vn.gmostore.web.client.util.ErrorHandlerAsyncCallback;

import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.NoResult;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class ProductsPresenter extends Presenter<MyView, MyProxy> implements ProductsUiHandlers, ProductAddedEvent.ProductAddedHandler,
        ActionBarEvent.ActionBarHandler {

    public interface MyView extends View, HasUiHandlers<ProductsUiHandlers> {
        void initDataProvider();

        void setProductsCount(Integer result);

        void displayProducts(int offset, List<ProductDto> products);

        HasData<ProductDto> getProductDisplay();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.products)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<ProductsPresenter> {
    }

    private final DispatchAsync dispatcher;
    private final ProductService productService;
    private final PlaceManager placeManager;
    private final ProductProxyFactory productProxyFactory;

    @Inject
    ProductsPresenter(EventBus eventBus, MyView view, MyProxy proxy, DispatchAsync dispatcher, ProductService carService, PlaceManager placeManager,
            ProductProxyFactory carProxyFactory) {
        super(eventBus, view, proxy);

        this.dispatcher = dispatcher;
        this.productService = carService;
        this.placeManager = placeManager;
        this.productProxyFactory = carProxyFactory;

        getView().setUiHandlers(this);
    }

    @Override
    public void onActionEvent(ActionBarEvent event) {
        if (event.getActionType() == ActionType.ADD && event.isTheSameToken(NameTokens.getProducts())) {
            placeManager.revealPlace(new Builder().nameToken(NameTokens.newProduct).build());
        }
    }

    @Override
    public void onEdit(ProductDto product) {
        //TODO: get detail product for edit
        //        ProductPresenter.MyProxy proxy = productProxyFactory.create(product, product.getId() + product.getFullName());

        //        placeManager.revealPlace(new Builder().nameToken(proxy.getNameToken()).build());
    }

    @Override
    public void onCreate() {
        placeManager.revealPlace(new Builder().nameToken(NameTokens.newProduct).build());
    }

    @Override
    public void fetchData(final int offset, int limit) {
        dispatcher.execute(productService.getProducts(offset, limit), new AbstractAsyncCallback<GetResults<ProductDto>>() {
            @Override
            public void onSuccess(GetResults<ProductDto> result) {
                getView().displayProducts(offset, result.getResults());
            }
        });
    }

    @Override
    public void onDelete(ProductDto product) {
        dispatcher.execute(productService.delete(product.getId()), new ErrorHandlerAsyncCallback<NoResult>(this) {
            @Override
            public void onSuccess(NoResult noResult) {
                fetchDataForDisplay();

                getView().setProductsCount(getView().getProductDisplay().getRowCount() - 1);
            }
        });
    }

    @ProxyEvent
    @Override
    public void onProductAdded(ProductAddedEvent event) {
        fetchDataForDisplay();

        if (event.isNew()) {
            getView().setProductsCount(getView().getProductDisplay().getRowCount() + 1);
        }
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ActionBarEvent.getType(), this);
        //        productProxyFactory.create(new ProductDto(), NameTokens.newProduct);
    }

    @Override
    protected void onReveal() {
        ActionBarVisibilityEvent.fire(this, true);
        ChangeActionBarEvent.fire(this, Arrays.asList(ActionType.ADD), true);
        getView().initDataProvider();

        dispatcher.execute(productService.getProductsCount(), new AbstractAsyncCallback<GetResult<NumberDto<Integer>>>() {
            @Override
            public void onSuccess(GetResult<NumberDto<Integer>> result) {
                getView().setProductsCount(result.getResult().getNumber());
            }
        });
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.SLOT_MAIN_CONTENT, this);
    }

    private void fetchDataForDisplay() {
        Range range = getView().getProductDisplay().getVisibleRange();
        fetchData(range.getStart(), range.getLength());
    }
}
