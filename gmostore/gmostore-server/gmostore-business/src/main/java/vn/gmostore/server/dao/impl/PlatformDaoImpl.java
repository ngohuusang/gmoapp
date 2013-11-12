/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
        return getCurrentSession().createCriteria(getEntityClass());
    }

    @Override
    public Platform getById(Integer id, boolean inTrash) {
        Platform platform = findById(id);
        if (!inTrash && platform.getDeleteDate() != null) {
            return null;
        }
        return platform;
    }

    @Override
    public List<Platform> getPlatforms(int offset, int limit, boolean inTrash) {

        offset = (offset < DEFAULT_OFFSET) ? DEFAULT_OFFSET : offset;
        limit = (limit <= 0 || limit > DEFAULT_LIMIT) ? DEFAULT_LIMIT : limit;

        Criteria criteria = createCriteria();

        if (!inTrash)
            criteria.add(Restrictions.isNull("deleteDate"));

        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);

        criteria.addOrder(Order.asc("order"));

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
        Platform platform = getById(platformId, true);
        if (platform != null)
            super.delete(platform);
    }

    @Override
    public void trash(Integer platformId) {
        Platform platform = getById(platformId, false);
        platform.setDeleteDate(new Date().getTime());

        if (platform != null) {
            update(platform, true);
        }
    }

    @Override
    public Platform save(Platform platform, boolean flush) {

        platform.setCreateDate(new Date().getTime());

        super.save(platform, flush);

        return platform;
    }

    @Override
    public void update(Platform platform, boolean flush) {

        platform.setUpdateDate(new Date().getTime());

        super.update(platform, flush);
    }

}
