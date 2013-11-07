package vn.gmostore.server.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Collections of utility methods that help dealing with DAO
 * 
 * @param <E>
 * @param <I>
 * 
 */

public interface AbstractDao<E, I extends Serializable> {

    /**
     * Uses this method when you want to have a query "COLUMN = queryDate" but
     * you don't want to interfere by the time part.
     */
    public Criterion equalIgnoreTime(String propertyName, Date queryDate);

    /**
     * Uses this method when you want to have a query "COLUMN = queryDate" but
     * you don't want to interfere by the time part.
     * 
     * <br>
     * We want to compare <b><code>propertyName</code> <= <code>queryDate</code>
     * </b>.</br>
     */
    public Criterion equalsOrLessThanIgnoreTime(String propertyName, Date queryDate);

    /**
     * Uses this method when you want to have a query "COLUMN = queryDate" but
     * you don't want to interfere by the time part.
     * 
     * <br>
     * We want to compare <b><code>propertyName</code> >= <code>queryDate</code>
     * </b>.</br>
     */
    public Criterion equalsOrGreaterThanIgnoreTime(String propertyName, Date queryDate);

    /**
     * Saves or updates the given persistent instance in a strong way: does the
     * same as the <code>saveOrUpdate(Object)</code> method, but throws a more
     * specific <code>OptimisticLockingFailureException</code> in the case of an
     * optimistic locking failure.
     * 
     * @see HibernateTemplate#saveOrUpdate(Object)
     * @param entity
     *            the persistent entity to save or update
     * @param objectName
     *            Name of the persistent object type.
     * @throws DataAccessException
     *             in case of Hibernate errors
     * @throws OptimisticLockingFailureException
     *             in case optimistic locking fails
     */
    public E saveOrUpdate(E entity) throws DataAccessException, DataIntegrityViolationException, OptimisticLockingFailureException;

    /**
     * Executes saveOrUpdate() and flush() on that entity.
     * 
     * @param entity
     *            The domain object to save or update
     * @return The saved or updated object
     * @throws DataAccessException
     * @throws DataIntegrityViolationException
     * @throws OptimisticLockingFailureException
     */
    public E saveOrUpdateAndFlush(E entity) throws DataAccessException, DataIntegrityViolationException, OptimisticLockingFailureException;

    /**
     * Counts the number of results of a search.
     * 
     * @param criteria
     *            The criteria for the query.
     * @return The number of results of the query.
     * @throws DataAccessException
     */
    public Integer findCountByCriteria(Criteria criteria) throws DataAccessException;

    /**
     * Retrieves all the domain objects matching the Hibernate criteria with
     * pagination
     * 
     * @param hibernateCriteria
     *            the criteria that the result has to fulfill
     * @return all object that fulfill the criteria by pagination
     * @throws DataAccessException
     * */
    public List<E> findByCriteria(Criteria criteria) throws DataAccessException;

    /**
     * Execute an HQL query, binding a number of values to "?" parameters. E.g
     * instead of write the query like <br>
     * <code>   
     *      String sql = " select [values] from [tables] where ....<br>
     *      Query query = getSession().createQuery(sql); <br>
            query.setParameter("param", value);<br>
     * 
     * </code> The new code will look like: just call
     * <code>findByQueryString(queryString,values)</code>
     * 
     * @param queryString
     *            a query expressed in Hibernate's query language
     * @param values
     *            the values of the parameters
     * @return all object that fulfill the criteria
     * @throws DataAccessException
     *             in case of Hibernate errors
     * 
     */
    public List<E> findByQueryString(String queryString) throws DataAccessException;

    /**
     * Find an entity by ID
     * 
     * @param id
     * @return
     */
    public E findById(I id) throws DataAccessException;

    /**
     * Find an entity by @NaturalId
     * 
     * @param value
     * @return
     */
    public E bySimpleNaturalId(Object value) throws DataAccessException;

    /**
     * Delete an entity
     * 
     * @param e
     */
    public void delete(E e) throws DataAccessException;

    /**
     * Provider search on Hibernate
     * 
     * @param text
     * @param columnNames
     * @param exact
     * @return
     * @throws DataAccessException
     */
    public List<E> searchBy(String text, String... columnNames) throws DataAccessException;

}
