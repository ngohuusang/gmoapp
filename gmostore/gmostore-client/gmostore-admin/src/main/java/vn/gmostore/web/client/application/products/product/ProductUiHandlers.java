package vn.gmostore.web.client.application.products.product;

import vn.gmostore.basic.dto.ProductDetailDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProductUiHandlers extends UiHandlers {
    void onSave(ProductDetailDto product);

    void onCancel();
}
