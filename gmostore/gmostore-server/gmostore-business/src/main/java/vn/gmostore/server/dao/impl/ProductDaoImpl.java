/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.util.OrderBy;
import vn.gmostore.basic.util.OrderBy.OrderType;
import vn.gmostore.server.dao.ProductDao;

/**
 *
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDaoImpl<Product, Integer> implements ProductDao {

    @Override
    protected Criteria createCriteria() {
        return getCurrentSession().createCriteria(getEntityClass());
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public Product getById(Integer id, boolean inTrash) {

        Product product = findById(id);

        if (!inTrash && product != null && product.getDeleteDate() != null) {
            throw new NoResultException("The product with id=" + id + " has been moved to trash!");
        } else if (product == null) {
            throw new NoResultException("The product with id=" + id + " is not found!");
        }

        return product;
    }

    @Override
    public void delete(Integer productId) {
        Product product = getById(productId, true);

        if (product != null) {
            super.delete(product);
        } else {
            throw new NoResultException("The product with id=" + productId + " is not found!");
        }

    }

    @Override
    public void trash(Integer productId) {
        Product product = getById(productId, false);

        if (product != null) {
            product.setDeleteDate(new Date().getTime());
            save(product, true);
        } else {
            throw new NoResultException("The product with id=" + productId + " is not found or has been moved to trash!");
        }
    }

    @Override
    public List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, OrderBy[] orderBys, boolean inTrash) {
        Criteria criteria = createCriteria();
        if (platformId != null)
            criteria.add(Restrictions.eq("platform.id", platformId));
        if (categoryId != null)
            criteria.add(Restrictions.eq("category.id", categoryId));

        offset = (offset < DEFAULT_OFFSET) ? DEFAULT_OFFSET : offset;
        limit = (limit <= 0 || limit > DEFAULT_LIMIT) ? DEFAULT_LIMIT : limit;

        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);

        if (orderBys != null && orderBys.length != 0) {
            for (OrderBy ob : orderBys) {
                if (ob.getOrderType() == OrderType.ASC) {
                    criteria.addOrder(Order.asc(ob.getColumn()));
                } else {
                    criteria.addOrder(Order.desc(ob.getColumn()));
                }
            }
        } else {
            criteria.addOrder(Order.desc("createDate"));
        }

        if (!inTrash) {
            criteria.add(Restrictions.isNull("deleteDate"));//Get product with delete_date is null
        }

        return findByCriteria(criteria);
    }

    @Override
    public Product save(Product product, boolean flush) {
        product.setCreateDate(new Date().getTime());
        product.setUpdateDate(new Date().getTime());

        super.save(product, flush);

        return product;
    }

    @Override
    public void update(Product product, boolean flush) {
        product.setUpdateDate(new Date().getTime());

        super.update(product, flush);
    }

    @Override
    public List<Product> search(String search, boolean exact, String... columnNames) {
        if (exact && columnNames.length != 0) {

            Criteria criteria = createCriteria();

            List<Criterion> predicates = new ArrayList<Criterion>(columnNames.length);

            for (String colName : columnNames) {
                predicates.add(Restrictions.eq(colName, search));
            }

            criteria.add(Restrictions.isNull("deleteDate"));//Get product with delete_date is null

            criteria.add(Restrictions.or(predicates.toArray(new Criterion[0])));

            return findByCriteria(criteria);
        }

        List<Product> products = searchBy(search, columnNames);
        List<Product> results = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getDeleteDate() == null) {
                results.add(product);
            }
        }

        return results;
    }

    @Override
    public Integer getProductsCount(Integer platformId, Integer categoryId, boolean inTrash) {
        Criteria criteria = createCriteria();

        if (platformId != null)
            criteria.add(Restrictions.eq("platform.id", platformId));
        if (categoryId != null)
            criteria.add(Restrictions.eq("category.id", categoryId));
        if (!inTrash)
            criteria.add(Restrictions.isNull("deleteDate"));//Get product with delete_date is null

        return findCountByCriteria(criteria);
    }
}
