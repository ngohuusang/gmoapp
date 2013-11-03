/**
 *
 */
package vn.gmostore.server.manager.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Product;
import vn.gmostore.server.dao.ProductDao;
import vn.gmostore.server.manager.ProductManager;

/**
 *
 *
 */
@Repository("productManager")
public class ProductManagerImpl implements ProductManager {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    ProductDao productDao;

    @Override
    public Product getById(Integer id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getProducts(int offset, int limit) {
        List<Product> products = productDao.getProducts(offset, limit);
        if (products != null) {
            return products;
        }

        return Collections.emptyList();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productDao.getProducts();
        if (products != null) {
            return products;
        }

        return Collections.emptyList();
    }

    @Override
    public Product saveOrUpdate(Product product, boolean flush) {
        return productDao.saveOrUpdate(product, flush);
    }

    @Override
    public List<Product> search(String search, boolean exact, String... columnNames) {
        List<Product> products = productDao.search(search, exact, columnNames);
        if (products != null) {
            return products;
        }

        return Collections.emptyList();
    }

    @Override
    public void delete(Product product) {
        productDao.delete(product);
    }
}
