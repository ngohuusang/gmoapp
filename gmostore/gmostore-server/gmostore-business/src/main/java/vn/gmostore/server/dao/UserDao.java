/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.User;

/**
 *
 */
public interface UserDao {

    public User getById(Integer id);

    public List<User> getAll();

    public void delete(User user);

    public User saveOrUpdate(User user, boolean flush);

    public User getUserByUsername(String username);
}
