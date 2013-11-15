/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.DownloadLink;
import vn.gmostore.server.dao.DownloadLinkDao;

/**
 *
 *
 */
@Repository
public class DownloadLinkDaoImpl extends AbstractDaoImpl<DownloadLink, Integer> implements DownloadLinkDao {

    @Override
    protected Class<DownloadLink> getEntityClass() {
        return DownloadLink.class;
    }

    @Override
    protected Criteria createCriteria() {
        return getCurrentSession().createCriteria(getEntityClass());
    }

    @Override
    public DownloadLink getById(Integer id, boolean inTrash) {
        DownloadLink downloadLink = findById(id);

        if (!inTrash && downloadLink != null && downloadLink.getDeleteDate() != null) {
            throw new NoResultException("Download link with id=" + id + " has been moved to trash!");
        } else if (downloadLink == null) {
            throw new NoResultException("Download link with id=" + id + " is not found!");
        }

        return downloadLink;
    }

    @Override
    public List<DownloadLink> getDownloadLinks(int offset, int limit, boolean inTrash) {

        offset = (offset < DEFAULT_OFFSET) ? DEFAULT_OFFSET : offset;
        limit = (limit <= 0 || limit > DEFAULT_LIMIT) ? DEFAULT_LIMIT : limit;

        Criteria criteria = createCriteria();

        if (!inTrash)
            criteria.add(Restrictions.isNull("deleteDate"));

        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit);

        criteria.addOrder(Order.desc("createDate"));

        return findByCriteria(criteria);
    }

    @Override
    public void delete(Integer id) {
        DownloadLink downloadLink = getById(id, true);
        if (downloadLink != null) {
            super.delete(downloadLink);
        } else {
            throw new NoResultException("Download link with id=" + id + " not found!");
        }
    }

    @Override
    public void trash(Integer id) {
        DownloadLink downloadLink = getById(id, false);
        if (downloadLink != null) {
            downloadLink.setDeleteDate(new Date().getTime());
            update(downloadLink, true);
        } else {
            throw new NoResultException("Download link with id=" + id + " not found or has been moved to trash!");
        }
    }

    @Override
    public DownloadLink save(DownloadLink downloadLink, boolean flush) {

        downloadLink.setCreateDate(new Date().getTime());
        downloadLink.setUpdateDate(new Date().getTime());

        super.save(downloadLink, flush);

        return downloadLink;
    }

    @Override
    public void update(DownloadLink downloadLink, boolean flush) {

        downloadLink.setUpdateDate(new Date().getTime());

        super.update(downloadLink, flush);
    }

    @Override
    public Map<Integer, Integer> getTopProductsDownloaded(Integer platformId, Integer categoryId, int limit) {
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        StringBuilder hql = new StringBuilder();
        hql.append("SELECT p.id, SUM(d.downloadCount) AS dc ");
        hql.append("FROM Product p, Version v, DownloadLink d ");
        hql.append("WHERE p.id = v.product.id and v.id = d.version.id ");
        if (platformId != null) {
            hql.append("AND p.platform.id = " + platformId + " ");
            hql.append("AND p.platform.deleteDate is null ");
        }
        if (categoryId != null) {
            hql.append("AND p.category.id = " + categoryId + " ");
            hql.append("AND p.category.deleteDate is null ");
        }
        hql.append("AND p.deleteDate is null AND v.deleteDate is null AND d.deleteDate is null ");
        hql.append("GROUP BY p.id ");
        hql.append("LIMIT " + limit + " ");
        hql.append("ORDER BY dc DESC");

        Query query = queryString(hql.toString());

        for (Iterator<?> it = query.iterate(); it.hasNext();) {
            Object[] rows = (Object[]) it.next();
            if (rows != null && rows.length == 2) {
                Integer productId = (Integer) rows[0];
                Integer downloadCount = (Integer) rows[1];
                results.put(productId, downloadCount);
            }

        }

        return results;
    }

    @Override
    public int getDownloadedCountBy(Integer productId) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT SUM(d.downloadCount) AS dc ");
        hql.append("FROM Product p, Version v, DownloadLink d ");
        hql.append("WHERE p.id=" + productId + " AND p.id = v.product.id AND v.id = d.version.id ");

        Query query = queryString(hql.toString());

        return (Integer) query.uniqueResult();
    }

}
