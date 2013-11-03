package vn.gmostore.web.client.application.products.product;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDetailDto;

import com.google.inject.Provider;

public class ProductPresenterProvider implements Provider<ProductPresenter> {
    private final ProductPresenterFactory productPresenterFactory;

    private ProductPresenter.MyProxy proxy;
    private ProductPresenter productPresenter;
    private ProductDetailDto product;

    @Inject
    ProductPresenterProvider(ProductPresenterFactory carPresenterFactory) {
        this.productPresenterFactory = carPresenterFactory;
    }

    @Override
    public ProductPresenter get() {
        assert proxy != null : "You must call setProxy first";

        if (productPresenter == null) {
            productPresenter = productPresenterFactory.create(proxy, product);
        }

        return productPresenter;
    }

    public void setProxy(ProductPresenter.MyProxy proxy) {
        this.proxy = proxy;
    }

    public void setProduct(ProductDetailDto product) {
        this.product = product;
    }
}
