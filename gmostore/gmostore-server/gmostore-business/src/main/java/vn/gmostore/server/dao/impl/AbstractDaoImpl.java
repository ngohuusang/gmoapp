package vn.gmostore.server.dao.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import vn.gmostore.server.dao.AbstractDao;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {
    
    protected static final int DEFAULT_OFFSET = 0;
    protected static final int DEFAULT_LIMIT = 1000;

    protected abstract Class<E> getEntityClass();

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Criterion equalIgnoreTime(String propertyName, Date queryDate) {
        Date dateNoTime = DateUtils.truncate(queryDate, Calendar.DATE);
        Date nextDayNoTime = DateUtils.addDays(dateNoTime, 1);

        // dateNoTime <= propertyName < nextDayNoTime
        return Restrictions.and(Restrictions.ge(propertyName, dateNoTime), Restrictions.lt(propertyName, nextDayNoTime));
    }

    @Override
    public Criterion equalsOrLessThanIgnoreTime(String propertyName, Date queryDate) {
        Date dateNoTime = DateUtils.truncate(queryDate, Calendar.DATE);
        Date nextDayNoTime = DateUtils.addDays(dateNoTime, 1);

        // < (dd.mm.yyyy 00:00:00 + 1d)
        return Restrictions.lt(propertyName, nextDayNoTime);
    }

    @Override
    public Criterion equalsOrGreaterThanIgnoreTime(String propertyName, Date queryDate) {
        Date dateNoTime = DateUtils.truncate(queryDate, Calendar.DATE);

        // >= dd.mm.yyyy 00:00:00
        return Restrictions.ge(propertyName, dateNoTime);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E findById(I id) {
        return (E) getCurrentSession().get(getEntityClass(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
        return e;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> findByCriteria(Criteria criteria) {
        Assert.notNull(criteria, "Criteria must not be null");
        return criteria.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E saveOrUpdateAndFlush(E entity) throws DataAccessException, DataIntegrityViolationException, OptimisticLockingFailureException {
        E tmp = saveOrUpdate(entity);
        getCurrentSession().flush();
        return tmp;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer findCountByCriteria(Criteria criteria) throws DataAccessException {

        criteria.setProjection(Projections.rowCount());

        Object result = criteria.uniqueResult();

        if (result == null) {
            result = 0;
        }

        return (Integer) result;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> findByQueryString(String queryString) throws DataAccessException {
        Assert.notNull(queryString, "Query string cannot be null!");
        Query query = getCurrentSession().createQuery(queryString);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public E bySimpleNaturalId(Object value) throws DataAccessException {
        Assert.notNull(value, "Value parameter cannot be null");
        return (E) getCurrentSession().bySimpleNaturalId(getEntityClass()).load(value);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<E> searchBy(String text, String... columnNames) throws DataAccessException {
        FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(getEntityClass()).get();
        // boostedTo: Give priority on search
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields(columnNames).matching(text).createQuery();

        // wrap Lucene query in a javax.persistence.Query
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, getEntityClass());

        return fullTextQuery.list();
    }

    protected abstract Criteria createCriteria();
}
