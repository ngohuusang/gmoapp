package vn.gmostore.web.client.application.products.renderer;

import vn.gmostore.basic.model.Product;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.inject.Inject;

public class ProductCell extends AbstractCell<Product> {
    interface Renderer extends UiRenderer {
        void render(SafeHtmlBuilder sb, String name);
    }

    private final Renderer uiRenderer;

    @Inject
    ProductCell(Renderer uiRenderer) {
        this.uiRenderer = uiRenderer;
    }

    @Override
    public void render(Context context, Product product, SafeHtmlBuilder safeHtmlBuilder) {
        uiRenderer.render(safeHtmlBuilder, product.getFullName());
    }
}
