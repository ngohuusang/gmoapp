package vn.gmostore.web.client.application.rating.ui;

import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.application.rating.ui.EditRatingPresenter.MyView;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;

public class EditRatingView extends PopupViewWithUiHandlers<EditRatingUiHandlers> implements MyView, Editor<RatingDto> {
    interface Binder extends UiBinder<Widget, EditRatingView> {
    }

    interface Driver extends SimpleBeanEditorDriver<RatingDto, EditRatingView> {
    }

    @UiField
    IntegerBox mark;
    @UiField(provided = true)
    ValueListBox<ProductDto> product;

    private final Driver driver;

    @Inject
    EditRatingView(Binder uiBinder, Driver driver, EventBus eventBus) {
        super(eventBus);

        //        product = new ValueListBox<ProductDto>(new ProductRenderer());
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);
    }

    @Override
    public void edit(RatingDto rating) {
        //        if (rating.getUser() == null) {
        //            rating.setProduct(product.getValue());
        //        }

        driver.edit(rating);
    }

    @Override
    public void setAllowedCars(List<ProductDto> products) {
        //        product.setValue(products.isEmpty() ? null : products.get(0));
        //        product.setAcceptableValues(products);
    }

    @UiHandler("save")
    void onSaveClicked(ClickEvent ignored) {
        getUiHandlers().onSave(driver.flush());
    }

    @UiHandler("close")
    void onCancelClicked(ClickEvent ignored) {
        getUiHandlers().onCancel();
    }
}
