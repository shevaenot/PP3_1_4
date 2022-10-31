package com.evgshev.spring.springrest.PP3_1_4.dao;


import com.evgshev.spring.springrest.PP3_1_4.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    Role getRoleById(long roleId);
}
