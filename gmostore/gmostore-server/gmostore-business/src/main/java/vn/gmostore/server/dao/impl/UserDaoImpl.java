/**
 *
 */
package vn.gmostore.server.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.User;
import vn.gmostore.server.dao.UserDao;

/**
 *
 *
 */
@Repository
//@Cacheable(value = "userDao")
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public List<User> getAll() {
        return findByCriteria(null);
    }

    @Override
    public User getById(Integer id) {
        return findById(id);
    }

    @Override
    public void delete(User user) {
        delete(user);
    }

    @Override
    public User saveOrUpdate(User user, boolean flush) {
        User result = saveOrUpdate(user);
        if (flush) {
            getCurrentSession().flush();
        }
        return result;
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> usersList = findByCriteria(Restrictions.eq("username", username));

        if (usersList != null && !usersList.isEmpty()) {
            return (User) usersList.get(0);
        }

        return null;
    }
}
