package vn.gmostore.web.client.application.rating.event;

import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.application.rating.event.RatingAddedEvent.RatingAddedHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class RatingAddedEvent extends GwtEvent<RatingAddedHandler> {
    public interface RatingAddedHandler extends EventHandler {
        void onRatingAdded(RatingAddedEvent event);
    }

    public static Type<RatingAddedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, RatingDto rating) {
        source.fireEvent(new RatingAddedEvent(rating));
    }

    private static final Type<RatingAddedHandler> TYPE = new Type<RatingAddedHandler>();

    private RatingDto rating;

    public RatingAddedEvent(RatingDto rating) {
        this.rating = rating;
    }

    @Override
    public Type<RatingAddedHandler> getAssociatedType() {
        return TYPE;
    }

    public RatingDto getRating() {
        return rating;
    }

    @Override
    protected void dispatch(RatingAddedHandler handler) {
        handler.onRatingAdded(this);
    }
}
