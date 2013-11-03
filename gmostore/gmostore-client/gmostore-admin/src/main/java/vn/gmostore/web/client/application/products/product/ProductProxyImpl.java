package vn.gmostore.web.client.application.products.product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vn.gmostore.web.client.application.products.product.navigation.NavigationTab;
import vn.gmostore.web.client.application.products.product.navigation.NavigationTabEvent;

import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.common.client.StandardProvider;
import com.gwtplatform.mvp.client.proxy.NotifyingAsyncCallback;
import com.gwtplatform.mvp.client.proxy.PlaceImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyImpl;
import com.gwtplatform.mvp.client.proxy.ProxyPlaceImpl;

public class ProductProxyImpl extends ProxyPlaceImpl<ProductPresenter> implements ProductPresenter.MyProxy {
    public static class WrappedProxy extends ProxyImpl<ProductPresenter> {
        @Inject
        public WrappedProxy(Provider<ProductPresenter> presenter) {
            this.presenter = new StandardProvider<ProductPresenter>(presenter);
        }
    }

    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    @Inject
    ProductProxyImpl(@Assisted
    ProductPresenterProvider carPresenterProvider, @Assisted
    String nameToken) {
        carPresenterProvider.setProxy(this);

        setProxy(new WrappedProxy(carPresenterProvider));
        setPlace(new PlaceImpl(nameToken));
    }

    @Override
    protected void bind(PlaceManager placeManager, final EventBus eventBus) {
        super.bind(placeManager, eventBus);

        NavigationTabEvent.NavigationTabHandler navigationTabHandler = new NavigationTabEvent.NavigationTabHandler() {
            @Override
            public void onCloseTab(NavigationTab element) {
                closeTab(element, eventBus);
            }

            @Override
            public void onRevealTab(NavigationTab element) {
            }
        };

        handlers.add(eventBus.addHandlerToSource(NavigationTabEvent.getType(), this, navigationTabHandler));
    }

    private void closeTab(final NavigationTab element, EventBus eventBus) {
        getPresenter(new NotifyingAsyncCallback<ProductPresenter>(eventBus) {
            @Override
            protected void success(ProductPresenter result) {
                if (element == result) {
                    unbind();
                }
            }
        });
    }

    private void unbind() {
        for (HandlerRegistration handler : handlers) {
            handler.removeHandler();
        }
    }
}
