/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.util.OrderBy;

/**
 *
 */
public interface ProductDao {

    List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, OrderBy[] orderBys, boolean inTrash);

    void delete(Integer productId);

    void trash(Integer productId);

    void update(Product product, boolean flush);

    Product save(Product product, boolean flush);

    List<Product> search(String search, boolean exact, String... columnNames);

    Integer getProductsCount(Integer platformId, Integer categoryId, boolean inTrash);

    Product getById(Integer id, boolean inTrash);

}
