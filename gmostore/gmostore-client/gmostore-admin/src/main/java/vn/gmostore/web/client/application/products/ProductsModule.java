package vn.gmostore.web.client.application.products;

import vn.gmostore.web.client.application.products.product.ProductPresenter;
import vn.gmostore.web.client.application.products.product.ProductPresenterFactory;
import vn.gmostore.web.client.application.products.product.ProductProxyImplFactory;
import vn.gmostore.web.client.application.products.product.ProductView;
import vn.gmostore.web.client.application.products.product.RootProductPresenter;
import vn.gmostore.web.client.application.products.product.RootProductView;
import vn.gmostore.web.client.application.products.product.navigation.NavigationTabPresenter;
import vn.gmostore.web.client.application.products.product.navigation.NavigationTabView;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ProductsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new GinFactoryModuleBuilder().build(ProductPresenterFactory.class));
        install(new GinFactoryModuleBuilder().build(ProductProxyImplFactory.class));

        bindPresenter(RootProductPresenter.class, RootProductPresenter.MyView.class, RootProductView.class, RootProductPresenter.MyProxy.class);
        bindPresenter(ProductsPresenter.class, ProductsPresenter.MyView.class, ProductsView.class, ProductsPresenter.MyProxy.class);

        bindSingletonPresenterWidget(NavigationTabPresenter.class, NavigationTabPresenter.MyView.class, NavigationTabView.class);

        bind(ProductPresenter.MyView.class).to(ProductView.class);

    }
}
