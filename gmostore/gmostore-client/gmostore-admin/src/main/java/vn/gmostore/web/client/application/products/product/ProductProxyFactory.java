package vn.gmostore.web.client.application.products.product;

import javax.inject.Inject;
import javax.inject.Provider;

import vn.gmostore.basic.dto.ProductDetailDto;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

public class ProductProxyFactory {
    private final Provider<ProductPresenterProvider> productPresenterProvider;
    private final PlaceManager placeManager;
    private final EventBus eventBus;
    private final ProductProxyImplFactory productProxyImplFactory;

    @Inject
    ProductProxyFactory(Provider<ProductPresenterProvider> productPresenterProvider, PlaceManager placeManager, EventBus eventBus,
            ProductProxyImplFactory productProxyImplFactory) {
        this.productPresenterProvider = productPresenterProvider;
        this.placeManager = placeManager;
        this.eventBus = eventBus;
        this.productProxyImplFactory = productProxyImplFactory;
    }

    public ProductPresenter.MyProxy create(ProductDetailDto product, String nameToken) {
        ProductPresenterProvider productPresenter = productPresenterProvider.get();
        productPresenter.setProduct(product);

        ProductProxyImpl productProxy = productProxyImplFactory.create(productPresenter, nameToken);
        productProxy.bind(placeManager, eventBus);

        return productProxy;
    }
}
