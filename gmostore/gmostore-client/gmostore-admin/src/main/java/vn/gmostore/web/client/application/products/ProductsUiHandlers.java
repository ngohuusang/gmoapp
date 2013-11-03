package vn.gmostore.web.client.application.products;

import vn.gmostore.basic.dto.ProductDto;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ProductsUiHandlers extends UiHandlers {
    void onEdit(ProductDto product);

    void onDelete(ProductDto product);

    void onCreate();

    void fetchData(int offset, int limit);
}
