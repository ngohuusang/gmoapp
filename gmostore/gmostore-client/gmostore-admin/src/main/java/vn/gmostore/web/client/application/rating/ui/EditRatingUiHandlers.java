package vn.gmostore.web.client.application.rating.ui;

import vn.gmostore.basic.dto.RatingDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface EditRatingUiHandlers extends UiHandlers {
    void createNew();

    void onSave(RatingDto rating);

    void onCancel();
}
