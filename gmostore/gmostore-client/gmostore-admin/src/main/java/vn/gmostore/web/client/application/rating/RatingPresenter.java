package vn.gmostore.web.client.application.rating;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.application.ApplicationPresenter;
import vn.gmostore.web.client.application.event.ActionBarEvent;
import vn.gmostore.web.client.application.event.ActionBarVisibilityEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent;
import vn.gmostore.web.client.application.event.ChangeActionBarEvent.ActionType;
import vn.gmostore.web.client.application.rating.event.RatingAddedEvent;
import vn.gmostore.web.client.application.rating.event.RatingAddedEvent.RatingAddedHandler;
import vn.gmostore.web.client.application.rating.ui.EditRatingPresenter;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.security.LoggedInGatekeeper;

import com.google.web.bindery.event.shared.EventBus;
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

public class RatingPresenter extends Presenter<RatingPresenter.MyView, RatingPresenter.MyProxy> implements RatingUiHandlers, RatingAddedHandler,
        ActionBarEvent.ActionBarHandler {

    public interface MyView extends View, HasUiHandlers<RatingUiHandlers> {
        void displayRatings(List<RatingDto> results);

        void removeRating(RatingDto rating);

        void addRating(RatingDto rating);
    }

    @ProxyCodeSplit
    @NameToken(NameTokens.rating)
    @UseGatekeeper(LoggedInGatekeeper.class)
    public interface MyProxy extends ProxyPlace<RatingPresenter> {
    }

    //    private final DispatchAsync dispatcher;
    private final EditRatingPresenter editRatingPresenter;
    //    private final RatingService ratingService;
    private final PlaceManager placeManager;

    @Inject
    RatingPresenter(EventBus eventBus, MyView view, MyProxy proxy, EditRatingPresenter editRatingPresenter, PlaceManager placeManager) {
        super(eventBus, view, proxy);

        //        this.dispatcher = dispatcher;
        //        this.ratingService = ratingService;
        this.placeManager = placeManager;
        this.editRatingPresenter = editRatingPresenter;

        getView().setUiHandlers(this);
    }

    @Override
    public void onActionEvent(ActionBarEvent event) {
        if (event.getActionType() == ActionType.ADD && event.isTheSameToken(NameTokens.getRating())) {
            placeManager.revealPlace(new Builder().nameToken(NameTokens.getDetailRating()).build());
        }
    }

    @Override
    public void onCreate() {
        editRatingPresenter.createNew();
    }

    @Override
    public void onDelete(final RatingDto rating) {
        //        dispatcher.execute(ratingService.delete(rating.getId()), new AbstractAsyncCallback<NoResult>() {
        //            @Override
        //            public void onSuccess(NoResult result) {
        //                getView().removeRating(rating);
        //            }
        //        });
    }

    @ProxyEvent
    @Override
    public void onRatingAdded(RatingAddedEvent event) {
        getView().addRating(event.getRating());
    }

    @Override
    protected void onReveal() {
        ActionBarVisibilityEvent.fire(this, true);
        ChangeActionBarEvent.fire(this, Arrays.asList(ActionType.ADD), true);

        //        dispatcher.execute(ratingService.getRatings(), new AbstractAsyncCallback<GetResults<RatingDto>>() {
        //            @Override
        //            public void onSuccess(GetResults<RatingDto> result) {
        //                getView().displayRatings(result.getResults());
        //            }
        //        });
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(ActionBarEvent.getType(), this);
    }

    @Override
    protected void revealInParent() {
        RevealContentEvent.fire(this, ApplicationPresenter.SLOT_MAIN_CONTENT, this);
    }
}
