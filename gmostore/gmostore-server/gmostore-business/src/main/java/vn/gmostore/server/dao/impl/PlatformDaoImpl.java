/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.Platform;
import vn.gmostore.server.dao.PlatformDao;

/**
 *
 *
 */
@Repository
public class PlatformDaoImpl extends AbstractDaoImpl<Platform, Integer> implements PlatformDao {

    @Override
    protected Class<Platform> getEntityClass() {
        return Platform.class;
    }

    @Override
    protected Criteria createCriteria() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.isNull("deleteDate"));

        return criteria;
    }

    @Override
    public Platform getById(Integer id) {
        return findById(id);
    }

    @Override
    public List<Platform> getPlatforms(int offset, int limit) {

        offset = (offset < DEFAULT_OFFSET) ? DEFAULT_OFFSET : offset;
        limit = (limit <= 0 || limit > DEFAULT_LIMIT) ? DEFAULT_LIMIT : limit;

        Criteria criteria = createCriteria();
        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);

        return findByCriteria(criteria);
    }

    @Override
    public Platform saveOrUpdate(Platform platform, boolean flush) {
        if (flush)
            return super.saveOrUpdateAndFlush(platform);
        else
            return super.saveOrUpdate(platform);
    }

    @Override
    public void delete(Integer platformId) {
        Platform platform = getById(platformId);
        if (platform != null)
            super.delete(platform);
    }

}
