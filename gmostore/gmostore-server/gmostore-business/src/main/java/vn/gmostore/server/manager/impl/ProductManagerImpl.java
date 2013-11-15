/**
 *
 */
package vn.gmostore.server.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import vn.gmostore.basic.dto.ProductSearchDto;
import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.util.OrderBy;
import vn.gmostore.server.dao.DownloadLinkDao;
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

    @Autowired
    DownloadLinkDao downloadLinkDao;

    @Override
    public Product getById(Integer id, boolean inTrash) {
        Assert.notNull(id, "The product id must be not null");
        logger.info("Get product by id=" + id);
        return productDao.getById(id, false);
    }

    @Override
    public List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, OrderBy[] orderBys) {
        Assert.notNull(platformId, "The product id must be not null");
        logger.info("Get products by platform=" + platformId + " category=" + categoryId + " offset=" + offset + " limit=" + limit);
        return productDao.getProducts(platformId, categoryId, offset, limit, orderBys, false);
    }

    @Override
    public void delete(Integer productId, boolean permalink) {
        Assert.notNull(productId, "Product id must be not null");
        if (permalink) {
            logger.info("Delete product with id=" + productId);
            productDao.delete(productId);
        } else {
            logger.info("Move product with id=" + productId + " to trash!");
            productDao.trash(productId);
        }
    }

    @Override
    public Product save(Product product, boolean flush) {
        Assert.notNull(product, "Product must be not null");

        logger.info("Save product with name=" + product.getFullName());
        Product saved = productDao.save(product, flush);
        logger.info("Product with id=" + saved.getId() + " is saved!");

        return saved;
    }

    @Override
    public void update(Product product, boolean flush) {
        Assert.notNull(product, "Platform must be not null");

        logger.info("Update product with id=" + product.getId());
        productDao.update(product, flush);

    }

    @Override
    public List<Product> search(String search, boolean exact, String... columnNames) {
        Assert.hasText(search, "Search string cannot be empty!");
        logger.info("Search '" + search + "' in comlum=" + columnNames + " exactly=" + exact);
        return productDao.search(search, exact, columnNames);
    }

    @Override
    public Integer getProductsCount(Integer platformId, Integer categoryId, boolean inTrash) {
        Assert.notNull(platformId, "Platform id cannot be null");
        logger.info("Get count product with platform=" + platformId + " in category=" + categoryId + " in trash=" + inTrash);
        return productDao.getProductsCount(platformId, categoryId, inTrash);
    }

    @Override
    public List<Product> getProductHot(Integer platformId, Integer categoryId, int limit) {
        Assert.notNull(platformId, "Platform id cannot be null");
        logger.info("Get count product with platform=" + platformId + " in category=" + categoryId + " in trash=");

        return null;
    }

    @Override
    public List<Product> getLatest(Integer platformId, Integer categoryId, int limit) {
        Assert.notNull(platformId, "Platform id cannot be null");
        logger.info("Get latest products with platform=" + platformId + " in category=" + categoryId);
        List<Product> products = getProducts(platformId, categoryId, 0, limit, ProductSearchDto.getLatestOrders());
        logger.info("Result=" + products.size());
        return products;
    }

    @Override
    public Map<Integer, Integer> getTopDownloaded(Integer platformId, Integer categoryId, int limit) {
        logger.info("Get top downloaded products id with platform=" + platformId + " in category=" + categoryId);
        Map<Integer, Integer> topDownloads = downloadLinkDao.getTopProductsDownloaded(platformId, categoryId, limit);
        logger.info("Result=" + topDownloads.size());
        return topDownloads;
    }

    @Override
    public List<Product> getTopViews(Integer platformId, Integer categoryId, int limit) {
        Assert.notNull(platformId, "Platform id cannot be null");
        logger.info("Get latest products with platform=" + platformId + " in category=" + categoryId);
        List<Product> products = getProducts(platformId, categoryId, 0, limit, ProductSearchDto.getTopViewsOrders());
        logger.info("Result=" + products.size());
        return products;
    }

    @Override
    public List<Product> getTopHot(Integer platformId, Integer categoryId, int limit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> getTopFree(Integer platformId, Integer categoryId, int limit) {
        // TODO Auto-generated method stub
        return null;
    }

}
