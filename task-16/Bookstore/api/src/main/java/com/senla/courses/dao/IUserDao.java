package com.senla.courses.dao;

import com.senla.courses.model.User;

public interface IUserDao extends GenericDao<User, Integer> {
    User findByUsername(String username);
}
