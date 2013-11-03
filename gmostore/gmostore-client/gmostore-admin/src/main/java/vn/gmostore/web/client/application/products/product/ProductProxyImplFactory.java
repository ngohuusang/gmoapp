package vn.gmostore.web.client.application.products.product;

public interface ProductProxyImplFactory {
    ProductProxyImpl create(ProductPresenterProvider carPresenterProvider, String nameToken);
}
