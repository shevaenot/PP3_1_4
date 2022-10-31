package com.evgshev.spring.springrest.PP3_1_4.controller;

import com.evgshev.spring.springrest.PP3_1_4.model.Role;
import com.evgshev.spring.springrest.PP3_1_4.model.User;
import com.evgshev.spring.springrest.PP3_1_4.service.RoleService;
import com.evgshev.spring.springrest.PP3_1_4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> pageOnlyForAdmins() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }



    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("role") List<Long> rolesId) {
        List<Role> roles = new ArrayList<>();
        for (long roleId : rolesId) {
            Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
        return userService.saveUser(user);
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-update";
    }
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role") List<Long> rolesId){
        List<Role> roles = new ArrayList<>();
        for (long roleId : rolesId) {
            Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }


}
