/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Rating;
import vn.gmostore.server.dao.RatingDao;

/**
 *
 *
 */
@Repository
public class RatingDaoImpl extends AbstractDaoImpl<Rating, Integer> implements RatingDao {

    @Override
    protected Class<Rating> getEntityClass() {
        return Rating.class;
    }

    @Override
    protected Criteria createCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return criteria;
    }

    @Override
    public Rating saveOrUpdate(Rating rating, boolean flush) {
        if (flush)
            return super.saveOrUpdateAndFlush(rating);
        else
            return saveOrUpdate(rating, flush);
    }

    @Override
    public void delete(Integer platformId) {
        Rating rating = findById(platformId);
        if (rating != null) {
            super.delete(rating);
        }

    }

    @Override
    public int getAverage(Integer productId) {
        Criteria criteria = createCriteria();
        criteria.setProjection(Projections.distinct(Projections.property("user.id")));
        criteria.add(Restrictions.eq("product.id", productId));
        criteria.setProjection(Projections.avg("mark"));
        Object result = criteria.uniqueResult();

        if (result != null) {
            return (Integer) result;
        }

        return 0;
    }

    @Override
    public Rating getBy(Integer productId, String username) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("product.id", productId));
        criteria.add(Restrictions.eq("user.username", username));

        List<Rating> ratings = findByCriteria(criteria);

        if (ratings.size() != 0)
            return ratings.get(0);

        return null;
    }
}
