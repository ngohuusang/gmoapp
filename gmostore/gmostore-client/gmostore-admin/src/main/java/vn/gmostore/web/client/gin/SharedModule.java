package vn.gmostore.web.client.gin;

import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.security.SecurityModule;

import com.gwtplatform.dispatch.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class SharedModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new SecurityModule());
        install(new RestDispatchAsyncModule.Builder().build());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.login);

        // Load and inject CSS resources
        bind(ResourceLoader.class).asEagerSingleton();
    }
}
