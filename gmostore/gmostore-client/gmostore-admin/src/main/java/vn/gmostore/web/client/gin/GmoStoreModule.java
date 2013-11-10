package vn.gmostore.web.client.gin;

import vn.gmostore.web.client.application.ApplicationModule;
import vn.gmostore.web.client.place.NameTokens;
import vn.gmostore.web.client.rest.RestModule;
import vn.gmostore.web.client.security.SecurityModule;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.GaAccount;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class GmoStoreModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new RestModule());
        install(new SecurityModule());
        install(new ApplicationModule());
        //        install(new RestDispatchAsyncModule.Builder().build());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.login);

        bindConstant().annotatedWith(GaAccount.class).to("UA-8319339-6");

        // Load and inject CSS resources
        bind(ResourceLoader.class).asEagerSingleton();

        //        bindConstant().annotatedWith(RestApplicationPath.class).to("http://localhost:8080/gmostore-api");
    }
}
