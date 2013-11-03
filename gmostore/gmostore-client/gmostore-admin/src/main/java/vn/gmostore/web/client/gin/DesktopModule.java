package vn.gmostore.web.client.gin;

import vn.gmostore.web.client.application.ApplicationModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class DesktopModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new ApplicationModule());
    }
}
