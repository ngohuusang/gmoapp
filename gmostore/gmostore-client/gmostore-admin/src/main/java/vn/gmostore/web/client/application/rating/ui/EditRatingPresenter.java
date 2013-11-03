package vn.gmostore.web.client.application.rating.ui;

import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.application.event.DisplayMessageEvent;
import vn.gmostore.web.client.application.rating.event.RatingAddedEvent;
import vn.gmostore.web.client.application.rating.ui.EditRatingPresenter.MyView;
import vn.gmostore.web.client.application.widget.message.Message;
import vn.gmostore.web.client.application.widget.message.MessageStyle;
import vn.gmostore.web.client.resources.EditRatingMessages;
import vn.gmostore.web.client.rest.ProductService;
import vn.gmostore.web.client.rest.RatingService;
import vn.gmostore.web.client.util.AbstractAsyncCallback;
import vn.gmostore.web.client.util.ErrorHandlerAsyncCallback;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

public class EditRatingPresenter extends PresenterWidget<MyView> implements EditRatingUiHandlers {
    public interface MyView extends PopupView, HasUiHandlers<EditRatingUiHandlers> {
        void edit(RatingDto rating);

        void setAllowedCars(List<ProductDto> products);
    }

    private final DispatchAsync dispatcher;
    private final ProductService productService;
    private final RatingService ratingService;
    private final EditRatingMessages messages;

    @Inject
    EditRatingPresenter(EventBus eventBus, MyView view, DispatchAsync dispatcher, ProductService productService, RatingService ratingService,
            EditRatingMessages messages) {
        super(eventBus, view);

        this.dispatcher = dispatcher;
        this.productService = productService;
        this.ratingService = ratingService;
        this.messages = messages;

        getView().setUiHandlers(this);
    }

    @Override
    public void createNew() {
        reveal();
    }

    @Override
    public void onCancel() {
        getView().hide();
    }

    @Override
    public void onSave(RatingDto rating) {
        dispatcher.execute(ratingService.saveOrCreate(rating), new ErrorHandlerAsyncCallback<GetResult<RatingDto>>(this) {
            @Override
            public void onSuccess(GetResult<RatingDto> result) {
                DisplayMessageEvent.fire(EditRatingPresenter.this, new Message(messages.ratingSaved(), MessageStyle.SUCCESS));
                RatingAddedEvent.fire(EditRatingPresenter.this, result.getResult());
                getView().hide();
            }
        });
    }

    private void reveal() {
        dispatcher.execute(productService.getProducts(), new AbstractAsyncCallback<GetResults<ProductDto>>() {
            @Override
            public void onSuccess(GetResults<ProductDto> result) {
                onGetCarsSuccess(result.getResults());
            }
        });
    }

    private void onGetCarsSuccess(List<ProductDto> products) {
        getView().setAllowedCars(products);
        getView().edit(new RatingDto());
        RevealRootPopupContentEvent.fire(this, this);
    }
}
