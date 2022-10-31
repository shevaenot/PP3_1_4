package com.evgshev.spring.springrest.PP3_1_4.dao;

import com.evgshev.spring.springrest.PP3_1_4.model.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getRoleById(long roleId) {
        return entityManager.createQuery("select r from Role r where r.id = :roleId", Role.class)
                .setParameter("roleId", roleId)
                .getSingleResult();
    }
}
