package vn.gmostore.web.server.guice;

import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {
    @Override
    public void configureServlets() {
        //        filter("/api/*").through(GuiceRestEasyFilterDispatcher.class);
    }
}
