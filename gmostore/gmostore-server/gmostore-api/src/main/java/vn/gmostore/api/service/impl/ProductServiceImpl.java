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
import vn.gmostore.server.manager.RatingManager;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductManager productManager;

    @Autowired
    RatingManager ratingManager;

    @Override
    public ProductDetailDto getProductDetailsBy(Integer platformId, Integer id) {
        if (id != null) {
            Product product = productManager.getById(id);
            return new ProductDetailDto(product);
        }
        return null;
    }

    @Override
    public List<ProductDto> getProducts(Integer platformId, Integer categoryId, int offset, int limit, String orderBy, String orderType) {
        if (offset >= 0 && limit >= 0) {
            List<Product> products = productManager.getProducts(platformId, categoryId, offset, limit, orderBy, orderType);
            List<ProductDto> result = new ArrayList<ProductDto>(products.size());
            for (Product product : products) {
                ProductDto productDto = new ProductDto(product);
                int ratedPoint = ratingManager.getAverage(product.getId());
                productDto.setRatedPoint(ratedPoint);

                result.add(productDto);
            }
            return result;
        }

        return Collections.emptyList();
    }

    @Override
    public Integer getProductsCount(Integer platformId, Integer categoryId) {
        return productManager.getProductsCount(platformId, categoryId);
    }

    @Override
    public ProductDetailDto saveOrCreate(ProductDetailDto product) {
        //        if (product != null)
        //            productManager.saveOrUpdate(product, true);//TODO
        return null;
    }

    @Override
    public void delete(Integer productId) {
        if (productId != null) {
            productManager.delete(productId);
        }

    }
}
