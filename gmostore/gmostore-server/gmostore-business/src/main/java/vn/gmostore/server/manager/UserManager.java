package vn.gmostore.server.manager;

import java.util.List;

import vn.gmostore.basic.model.User;

public interface UserManager {

    public User saveOrUpdate(User user);

    public List<User> getAllUser();

    public void deleteUser(Integer userId);

    public User getUserByUsername(String username);
}
