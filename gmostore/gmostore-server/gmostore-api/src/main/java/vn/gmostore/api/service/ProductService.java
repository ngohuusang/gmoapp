package vn.gmostore.api.service;

import java.util.List;

import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.basic.dto.ProductDto;

public interface ProductService {

    ProductDetailDto getProductDetailsBy(Integer platformId, Integer productId);

    List<ProductDto> getProducts(Integer platformId, Integer categoryId, int offset, int limit, String orderBy, String orderType);

    ProductDto saveOrCreate(ProductDetailDto product);

    void delete(Integer productId);

    Integer getProductsCount(Integer platformId, Integer categoryId);
}
