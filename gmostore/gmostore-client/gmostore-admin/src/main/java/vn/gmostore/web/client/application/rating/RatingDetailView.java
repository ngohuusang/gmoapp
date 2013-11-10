package vn.gmostore.web.client.application.rating;

import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.basic.dto.RatingDto;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class RatingDetailView extends ViewWithUiHandlers<RatingDetailUiHandlers> implements RatingDetailPresenter.MyView, Editor<RatingDto> {
    interface Binder extends UiBinder<Widget, RatingDetailView> {
    }

    interface Driver extends SimpleBeanEditorDriver<RatingDto, RatingDetailView> {
    }

    @UiField
    IntegerBox mark;
    @UiField(provided = true)
    ValueListBox<ProductDto> product;

    private final Driver driver;

    @Inject
    public RatingDetailView(Binder uiBinder, Driver driver) {
        this.driver = driver;

        //        product = new ValueListBox<ProductDto>(new ProductRenderer());

        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);

        mark.getElement().setAttribute("placeholder", "Your rating");
    }

    @Override
    public void edit(RatingDto rating) {
        //        if (rating.getProduct() == null) {
        //            rating.setProduct(product.getValue());
        //        }

        driver.edit(rating);
    }

    @Override
    public void setAllowedProducts(List<ProductDto> products) {
        product.setValue(products.isEmpty() ? null : products.get(0));
        product.setAcceptableValues(products);
    }

    @Override
    public void getRating() {
        getUiHandlers().onSave(driver.flush());
    }
}
