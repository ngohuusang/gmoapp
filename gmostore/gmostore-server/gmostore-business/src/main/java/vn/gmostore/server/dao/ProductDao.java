/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.Product;

/**
 *
 */
public interface ProductDao {

    public Product getById(Integer id);

    public List<Product> getProducts(int offset, int limit);

    public List<Product> getProducts();

    public void delete(Product product);

    public Product saveOrUpdate(Product product, boolean flush);

    public List<Product> search(String search, boolean exact, String... columnNames);
}