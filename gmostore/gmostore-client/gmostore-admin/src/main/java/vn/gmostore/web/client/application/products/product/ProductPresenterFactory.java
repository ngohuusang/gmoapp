package vn.gmostore.web.client.application.products.product;

import vn.gmostore.basic.dto.ProductDetailDto;

public interface ProductPresenterFactory {
    ProductPresenter create(ProductPresenter.MyProxy proxy, ProductDetailDto product);
}
