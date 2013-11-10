package vn.gmostore.web.client.application.products.product;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.web.client.application.products.product.ProductPresenter.MyView;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ProductView extends ViewWithUiHandlers<ProductUiHandlers> implements MyView, Editor<ProductDetailDto> {
    interface Binder extends UiBinder<Widget, ProductView> {
    }

    interface Driver extends SimpleBeanEditorDriver<ProductDetailDto, ProductView> {
    }

    @UiField
    TextBox fullName;
    //    @UiField(provided = true)
    //    ValueListBox<ManufacturerDto> manufacturer;
    //    @UiField
    //    CarPropertiesEditor carProperties;

    private final Driver driver;

    @Inject
    ProductView(Binder uiBinder, Driver driver) {
        //        manufacturer = new ValueListBox<ManufacturerDto>(new ManufacturerRenderer());
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));

        driver.initialize(this);
    }

    @Override
    public void edit(ProductDetailDto product) {
        //        if (user.getManufacturer() == null) {
        //            user.setManufacturer(manufacturer.getValue());
        //        }

        driver.edit(product);
    }

    //    @Override
    //    public void setAllowedManufacturers(List<ManufacturerDto> manufacturerDtos) {
    //        manufacturer.setValue(manufacturerDtos.isEmpty() ? null : manufacturerDtos.get(0));
    //        manufacturer.setAcceptableValues(manufacturerDtos);
    //    }

    @Override
    public void resetFields(ProductDetailDto product) {
        driver.edit(product);
    }

    @Override
    public void getProduct() {
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
