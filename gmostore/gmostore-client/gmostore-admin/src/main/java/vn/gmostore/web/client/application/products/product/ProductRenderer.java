package vn.gmostore.web.client.application.products.product;

import vn.gmostore.basic.model.Product;

import com.google.gwt.text.shared.AbstractRenderer;

public class ProductRenderer extends AbstractRenderer<Product> {
    @Override
    public String render(Product product) {
        if (product == null) {
            return "";
        }

        //        ManufacturerRenderer manufacturerRenderer = new ManufacturerRenderer();
        //        String manufacturer = manufacturerRenderer.render(product.getManufacturer());

        return product.getId() + " " + product.getFullName();
    }
}
