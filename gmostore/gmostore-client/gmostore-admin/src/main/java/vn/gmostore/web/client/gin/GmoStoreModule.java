package vn.gmostore.web.client.gin;

import com.gwtplatform.dispatch.client.rest.RestApplicationPath;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class GmoStoreModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new SharedModule());

        bindConstant().annotatedWith(RestApplicationPath.class).to("http://localhost:8080/gmostore-api");
    }
}
