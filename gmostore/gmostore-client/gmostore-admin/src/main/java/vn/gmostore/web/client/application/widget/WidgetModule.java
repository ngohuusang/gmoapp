package vn.gmostore.web.client.application.widget;

import vn.gmostore.web.client.application.widget.header.HeaderPresenter;
import vn.gmostore.web.client.application.widget.header.HeaderUiHandlers;
import vn.gmostore.web.client.application.widget.header.HeaderView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class);

        bind(HeaderUiHandlers.class).to(HeaderPresenter.class);
    }
}
