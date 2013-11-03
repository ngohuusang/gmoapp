package vn.gmostore.web.client.application.rating;

import vn.gmostore.basic.dto.RatingDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface RatingUiHandlers extends UiHandlers {
    void onDelete(RatingDto rating);

    void onCreate();
}
