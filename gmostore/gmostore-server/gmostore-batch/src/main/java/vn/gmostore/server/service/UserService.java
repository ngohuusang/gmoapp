package vn.gmostore.server.service;

import java.util.List;

import vn.gmostore.basic.model.User;

public interface UserService {
    public User saveOrUpdate(User user);

    public List<User> getAllUser();

    public void deleteUser(Integer userId);

    public User getUserByUsername(String username);
}
