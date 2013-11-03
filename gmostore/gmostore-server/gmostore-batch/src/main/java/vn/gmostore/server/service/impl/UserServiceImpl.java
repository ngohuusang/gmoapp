package vn.gmostore.server.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.gmostore.basic.model.User;
import vn.gmostore.server.manager.UserManager;
import vn.gmostore.server.service.UserService;

@Service("userService")
@Transactional(rollbackFor = Throwable.class, readOnly = false)
public class UserServiceImpl implements UserService {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserManager userManager;

    @Override
    public User saveOrUpdate(User user) {
        logger.info("Save user" + user.getUsername());
        return userManager.saveOrUpdate(user);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Get all users");
        return userManager.getAllUser();
    }

    @Override
    public void deleteUser(Integer userId) {
        logger.info("Delete user with id:" + userId);
        userManager.deleteUser(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info("Get user by username:" + username);
        return userManager.getUserByUsername(username);
    }

}
