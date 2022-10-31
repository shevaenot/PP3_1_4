package com.evgshev.spring.springrest.PP3_1_4.controller;

import com.evgshev.spring.springrest.PP3_1_4.model.User;
import com.evgshev.spring.springrest.PP3_1_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user")
    public User pageOnlyOfUsers(Principal principal) {
        User user = userService.findUserByLogin(principal.getName());
        return user;
    }

}
