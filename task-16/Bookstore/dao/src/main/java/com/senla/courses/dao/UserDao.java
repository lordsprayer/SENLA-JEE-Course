package com.senla.courses.dao;

import com.senla.courses.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends  AbstractDao<User, Integer> implements IUserDao{

    private static final Logger log = LogManager.getLogger(UserDao.class);

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }

    @Override
    public User findByUsername(String username) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Predicate usernamePredicate = builder.equal(root.get("username"), username);
            query.select(root).where(usernamePredicate);
            TypedQuery<User> findUserQuery = entityManager.createQuery(query);
            return findUserQuery.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
