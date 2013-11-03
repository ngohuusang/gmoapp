package vn.gmostore.web.client.application.products.event;

import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.web.client.application.products.event.ProductAddedEvent.ProductAddedHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ProductAddedEvent extends GwtEvent<ProductAddedHandler> {
    public interface ProductAddedHandler extends EventHandler {
        void onProductAdded(ProductAddedEvent event);
    }

    public static Type<ProductAddedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, ProductDetailDto product) {
        fire(source, product, false);
    }

    public static void fire(HasHandlers source, ProductDetailDto product, Boolean isNew) {
        source.fireEvent(new ProductAddedEvent(product, isNew));
    }

    private static final Type<ProductAddedHandler> TYPE = new Type<ProductAddedHandler>();

    private final ProductDetailDto product;
    private final Boolean isNew;

    public ProductAddedEvent(ProductDetailDto product) {
        this(product, false);
    }

    public ProductAddedEvent(ProductDetailDto product, Boolean isNew) {

        this.product = product;
        this.isNew = isNew;
    }

    @Override
    public Type<ProductAddedHandler> getAssociatedType() {
        return TYPE;
    }

    public ProductDetailDto getProduct() {
        return product;
    }

    public Boolean isNew() {
        return isNew;
    }

    @Override
    protected void dispatch(ProductAddedHandler handler) {
        handler.onProductAdded(this);
    }
}
