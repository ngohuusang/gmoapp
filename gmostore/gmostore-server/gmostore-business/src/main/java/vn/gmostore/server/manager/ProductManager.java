/**
 *
 */
package vn.gmostore.server.manager;

import java.util.List;
import java.util.Map;

import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.util.OrderBy;

/**
 *
 */
public interface ProductManager {

    Product getById(Integer id, boolean inTrash);

    List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, OrderBy[] orderBy);

    void delete(Integer productId, boolean permalink);

    Product save(Product product, boolean flush);

    void update(Product product, boolean flush);

    List<Product> search(String search, boolean exact, String... columnNames);

    Integer getProductsCount(Integer platformId, Integer categoryId, boolean inTrash);

    List<Product> getProductHot(Integer platformId, Integer categoryId, int limit);

    List<Product> getLatest(Integer platformId, Integer categoryId, int limit);

    Map<Integer, Integer> getTopDownloaded(Integer platformId, Integer categoryId, int limit);

    List<Product> getTopViews(Integer platformId, Integer categoryId, int limit);

    List<Product> getTopHot(Integer platformId, Integer categoryId, int limit);

    List<Product> getTopFree(Integer platformId, Integer categoryId, int limit);

}
