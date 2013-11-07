/**
 *
 */
package vn.gmostore.server.manager;

import java.util.List;

import vn.gmostore.basic.model.Product;

/**
 *
 */
public interface ProductManager {

    public Product getById(Integer id);

    public List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, String orderBy, String orderType);

    public void delete(Integer productId);

    public Product saveOrUpdate(Product product, boolean flush);

    public List<Product> search(String search, boolean exact, String... columnNames);

    public Integer getProductsCount(Integer platformId, Integer categoryId);
}
