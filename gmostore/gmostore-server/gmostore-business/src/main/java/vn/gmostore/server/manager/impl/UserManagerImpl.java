package vn.gmostore.server.manager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.gmostore.basic.model.User;
import vn.gmostore.server.dao.UserDao;
import vn.gmostore.server.manager.UserManager;

@Repository("userManager")
public class UserManagerImpl implements UserManager {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDao userDao;

    @Override
    public User saveOrUpdate(User user) {
        logger.info("Save user" + user.getUsername());
        return userDao.saveOrUpdate(user, true);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Get all users");
        return userDao.getAll();
    }

    @Override
    public void deleteUser(Integer userId) {
        logger.info("Delete user with id:" + userId);
        User user = userDao.getById(userId);
        userDao.delete(user);
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Get user by username:" + username);
        return userDao.getUserByUsername(username);
    }

}
