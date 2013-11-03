package vn.gmostore.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gmostore.api.service.ProductService;
import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.basic.model.Product;
import vn.gmostore.server.manager.ProductManager;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductManager productManager;

    @Override
    public ProductDetailDto getProductDetailsBy(Integer id) {
        if (id != null) {
            Product product = productManager.getById(id);
            return new ProductDetailDto(product);
        }
        return null;
    }

    @Override
    public List<ProductDto> getProducts(int offset, int limit) {
        if (offset >= 0 && limit >= 0) {
            List<Product> products = productManager.getProducts(offset, limit);
            List<ProductDto> result = new ArrayList<ProductDto>(products.size());
            for (Product product : products) {
                result.add(new ProductDto(product));
            }
            return result;
        }

        return Collections.emptyList();
    }

    @Override
    public Integer getProductsCount() {
        return 0;
    }

    @Override
    public ProductDetailDto saveOrCreate(ProductDetailDto product) {
        //        if (product != null)
        //            productManager.saveOrUpdate(product, true);
        return null;
    }

    @Override
    public void delete(Integer productId) {
        if (productId != null) {
            Product product = productManager.getById(productId);
            productManager.delete(product);
        }

    }
}
