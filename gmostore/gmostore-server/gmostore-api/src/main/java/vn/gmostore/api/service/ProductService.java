package vn.gmostore.api.service;

import java.util.List;

import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.basic.dto.ProductDto;

public interface ProductService {
    ProductDetailDto getProductDetailsBy(Integer id);

    List<ProductDto> getProducts(int offset, int limit);

    Integer getProductsCount();

    ProductDto saveOrCreate(ProductDetailDto product);

    void delete(Integer productId);
}
