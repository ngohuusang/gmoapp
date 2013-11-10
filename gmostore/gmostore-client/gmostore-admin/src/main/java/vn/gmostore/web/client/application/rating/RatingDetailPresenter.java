package vn.gmostore.web.client.application.rating;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.application.ApplicationPresenter;
import vn.gmostore.web.client.application.event.ActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent.ActionType;
import vn.gmostore.web.client.application.event.GoBackEvent;
import vn.gmostore.web.client.application.rating.RatingDetailPresenter.MyProxy;
import vn.gmostore.web.client.application.rating.RatingDetailPresenter.MyView;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.resources.EditRatingMessages;
import vn.gmostore.web.client.security.LoggedInGatekeeper;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest.Builder;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class RatingDetailPresenter extends Presenter<MyView, MyProxy> implements RatingDetailUiHandlers, ActionBarEvent.ActionBarHandler,
        GoBackEvent.GoBackHandler {

    public interface MyView extends View, HasUiHandlers<RatingDetailUiHandlers> {
        void edit(RatingDto rating);

        void setAllowedProducts(List<ProductDto> products);

        void getRating();
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.detailRating)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<RatingDetailPresenter> {
    }

    //    private final DispatchAsync dispatcher;
    //    private final ProductService productService;
    //    private final RatingService ratingService;
    private final EditRatingMessages messages;
    private final PlaceManager placeManager;

    @Inject
    RatingDetailPresenter(EventBus eventBus, MyView view, MyProxy proxy, EditRatingMessages messages, PlaceManager placeManager) {
        super(eventBus, view, proxy);
        //
        //        this.dispatcher = dispatcher;
        //        this.productService = productService;
        //        this.ratingService = ratingService;
        this.messages = messages;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void onGoBack(GoBackEvent event) {
        placeManager.revealPlace(new Builder().nameToken(NameTokens.getRating()).build());
    }

    @Override
    public void onActionEvent(ActionBarEvent event) {
        if (event.getActionType() == ActionType.DONE && event.isTheSameToken(NameTokens.getDetailRating())) {
            getView().getRating();
        }
    }

    @Override
    public void onSave(RatingDto rating) {
        //        dispatcher.execute(ratingService.saveOrCreate(rating), new ErrorHandlerAsyncCallback<GetResult<RatingDto>>(this) {
        //            @Override
        //            public void onSuccess(GetResult<RatingDto> result) {
        //                DisplayMessageEvent.fire(RatingDetailPresenter.this, new Message(messages.ratingSaved(), MessageStyle.SUCCESS));
        //                placeManager.revealPlace(new Builder().nameToken(NameTokens.getRating()).build());
        //            }
        //        });
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(GoBackEvent.getType(), this);
        addRegisteredHandler(ActionBarEvent.getType(), this);
    }

    @Override
    protected void onReveal() {
        List<ActionType> actions = Arrays.asList(ActionType.DONE);
        ChangeActionBarEvent.fire(this, actions, false);

        //        dispatcher.execute(productService.getProducts(), new AbstractAsyncCallback<GetResults<ProductDto>>() {
        //            @Override
        //            public void onSuccess(GetResults<ProductDto> result) {
        //                onGetCarsSuccess(result.getResults());
        //            }
        //        });
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.SLOT_MAIN_CONTENT, this);
    }

    private void onGetCarsSuccess(List<ProductDto> products) {
        getView().setAllowedProducts(products);
        getView().edit(new RatingDto());
    }
}
