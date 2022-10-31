package com.evgshev.spring.springrest.PP3_1_4.service;



import com.evgshev.spring.springrest.PP3_1_4.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(long roleId);
}
