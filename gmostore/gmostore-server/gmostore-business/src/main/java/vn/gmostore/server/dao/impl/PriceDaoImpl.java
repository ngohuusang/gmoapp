/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Price;
import vn.gmostore.server.dao.PriceDao;

/**
 *
 *
 */
@Repository
public class PriceDaoImpl extends AbstractDaoImpl<Price, Integer> implements PriceDao {

    @Override
    protected Class<Price> getEntityClass() {
        return Price.class;
    }

    @Override
    protected Criteria createCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.isNull("deleteDate"));
        return criteria;
    }

    @Override
    public Price getLatestBy(Integer productId) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("product.id", productId));
        criteria.setProjection(Projections.max("createDate"));
        Object result = criteria.uniqueResult();

        if (result != null) {
            return (Price) result;
        }

        return null;
    }

    @Override
    public Price save(Price price, boolean flush) {//TODO
        if (flush)
            return super.saveOrUpdateAndFlush(price);
        else
            return super.saveOrUpdate(price);
    }

    @Override
    public List<Price> getPrices(Integer productId) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("product.id", productId));

        return findByCriteria(criteria);
    }

    @Override
    public void delete(Integer priceId) {
        Price price = findById(priceId);

        if (price != null) {
            price.setDeleteDate(new Date().getTime());
            saveOrUpdateAndFlush(price);

            return;
        }

        throw new IllegalArgumentException("The price with id=" + priceId + " is not found!");

    }

    @Override
    public void trash(Integer priceId) {
        // TODO Auto-generated method stub
    }
}
