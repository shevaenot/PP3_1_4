package com.evgshev.spring.springrest.PP3_1_4.dao;

import com.evgshev.spring.springrest.PP3_1_4.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByLogin(String login) {
        return entityManager.createQuery("select u from User u join fetch u.roles where u.username = :userName", User.class)
                .setParameter("userName", login)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.createQuery("delete from User u where u.id =:userId")
                .setParameter("userId", id)
                .executeUpdate();
    }
}
