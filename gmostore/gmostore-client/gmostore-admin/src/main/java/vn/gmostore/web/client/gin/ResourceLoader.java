package vn.gmostore.web.client.gin;

import vn.gmostore.web.client.resources.AppResources;

import com.google.inject.Inject;

public class ResourceLoader {
    @Inject
    public ResourceLoader(AppResources resources) {
        resources.styles().ensureInjected();
    }
}
