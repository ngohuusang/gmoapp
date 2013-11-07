/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.util.OrderBy;
import vn.gmostore.server.dao.ProductDao;

/**
 *
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDaoImpl<Product, Integer> implements ProductDao {

    @Override
    protected Criteria createCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.isNull("deleteDate"));//Get product with delete_date is null
        return criteria;
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    public Product getById(Integer id) {
        return findById(id);
    }

    @Override
    public void delete(Integer productId) {
        Product product = getById(productId);

        if (product != null) {
            product.setDeleteDate(new Date().getTime());
            saveOrUpdateAndFlush(product);

            return;
        }

        throw new IllegalArgumentException("The product with id=" + productId + " is not found!");
    }

    @Override
    public List<Product> getProducts(Integer platformId, Integer categoryId, int offset, int limit, String orderBy, String orderType) {
        Criteria criteria = createCriteria();
        if (platformId != null)
            criteria.add(Restrictions.eq("platform.id", platformId));
        if (categoryId != null)
            criteria.add(Restrictions.eq("category.id", categoryId));
        if (offset >= 0)
            criteria.setFirstResult(offset);
        if (limit > 0)
            criteria.setMaxResults(limit);

        if (orderBy == null || orderBy.trim().isEmpty())
            orderBy = "createDate";

        if (orderType != null && orderType.equalsIgnoreCase(OrderBy.ASC.toString())) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }

        return findByCriteria(criteria);
    }

    @Override
    public Product saveOrUpdate(Product product, boolean flush) {
        if(flush)
            return super.saveOrUpdateAndFlush(product);
        else
        return saveOrUpdate(product, flush);
    }

    @Override
    public List<Product> search(String search, boolean exact, String... columnNames) {
        if (exact && columnNames.length != 0) {

            Criteria criteria = createCriteria();

            List<Criterion> predicates = new ArrayList<Criterion>(columnNames.length);

            for (String colName : columnNames) {
                predicates.add(Restrictions.eq(colName, search));
            }

            criteria.add(Restrictions.or(predicates.toArray(new Criterion[0])));

            return findByCriteria(criteria);
        }

        return searchBy(search, columnNames);
    }

    @Override
    public Integer getProductsCount(Integer platformId, Integer categoryId) {
        Criteria criteria = createCriteria();

        if (platformId != null)
            criteria.add(Restrictions.eq("platform.id", platformId));
        if (categoryId != null)
            criteria.add(Restrictions.eq("category.id", categoryId));

        return findCountByCriteria(criteria);
    }
}
