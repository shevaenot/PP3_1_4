package com.evgshev.spring.springrest.PP3_1_4.service;

import com.evgshev.spring.springrest.PP3_1_4.dao.RoleDao;
import com.evgshev.spring.springrest.PP3_1_4.model.Role;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleById(long roleId) {
        return roleDao.getRoleById(roleId);
    }
}
