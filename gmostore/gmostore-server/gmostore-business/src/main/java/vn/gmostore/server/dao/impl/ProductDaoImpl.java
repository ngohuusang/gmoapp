/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Product;
import vn.gmostore.server.dao.ProductDao;

/**
 *
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDaoImpl<Product, Integer> implements ProductDao {

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public Product getById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Product> getProducts(int offset, int limit) {
        return findByCriteria(null, offset, limit);
    }

    @Override
    public List<Product> getProducts() {
        return findByCriteria(null);
    }

    @Override
    public Product saveOrUpdate(Product product, boolean flush) {
        return saveOrUpdate(product, flush);
    }

    @Override
    public List<Product> search(String search, boolean exact, String... columnNames) {
        return search(search, exact, columnNames);
    }
}
