package vn.gmostore.web.client.rest;

import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RestModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("rest")).to("http://localhost:8080/gmostore-api");
    }

    @Provides
    @Singleton
    @Inject
    public ProductResourceAsync providerProductResource(@Named("rest")
    String url) {
        ProductResourceAsync productResourceAsync = GWT.create(ProductResourceAsync.class);
        //        Map<String, String> headers = new HashMap<String, String>();
        //        headers.put("Content-Type", Resource.CONTENT_TYPE_JSON);
        //        Resource resource = new Resource(url, headers);
        Resource resource = new Resource(url);
        ((RestServiceProxy) productResourceAsync).setResource(resource);
        return productResourceAsync;
    }
}
