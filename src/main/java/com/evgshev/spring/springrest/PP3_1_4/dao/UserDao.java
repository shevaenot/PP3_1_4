package com.evgshev.spring.springrest.PP3_1_4.dao;

import com.evgshev.spring.springrest.PP3_1_4.model.User;
import java.util.List;

public interface UserDao {

    User findUserByLogin(String login);

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
