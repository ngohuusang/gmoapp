package vn.gmostore.web.client.application;

import vn.gmostore.web.client.application.login.LoginModule;
import vn.gmostore.web.client.application.products.ProductsModule;
import vn.gmostore.web.client.application.rating.RatingModule;
import vn.gmostore.web.client.application.widget.WidgetModule;
import vn.gmostore.web.client.application.widget.message.MessagesModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new LoginModule());
        install(new ProductsModule());
        install(new RatingModule());
        install(new WidgetModule());
        install(new MessagesModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
