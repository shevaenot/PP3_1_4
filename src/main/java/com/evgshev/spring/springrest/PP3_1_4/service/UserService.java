package com.evgshev.spring.springrest.PP3_1_4.service;



import com.evgshev.spring.springrest.PP3_1_4.model.User;

import java.util.List;

public interface UserService {

    User findUserByLogin(String login);

    List<User> getAllUsers();

    String saveUser(User user);

    void updateUser(User user);

    User getUser(long id);

    void deleteUser(long id);
}
