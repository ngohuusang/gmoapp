package vn.gmostore.web.client.application.products.product.widget;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductPropertiesDto;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class ProductPropertiesEditor extends Composite implements Editor<ProductPropertiesDto> {
    interface Binder extends UiBinder<Widget, ProductPropertiesEditor> {
    }

    @UiField
    TextBox someString;
    @UiField
    IntegerBox someNumber;
    @UiField
    DateBox someDate;

    @Inject
    ProductPropertiesEditor(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        someString.getElement().setAttribute("placeholder", "Property #1");
        someNumber.getElement().setAttribute("placeholder", "Property #2");
        someDate.getElement().setAttribute("placeholder", "Property #3");
    }
}
