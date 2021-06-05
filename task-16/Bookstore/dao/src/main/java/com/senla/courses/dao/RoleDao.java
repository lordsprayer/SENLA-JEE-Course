package com.senla.courses.dao;

import com.senla.courses.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AbstractDao<Role, Integer> implements IRoleDao {
    @Override
    protected Class<Role> getClazz() {
        return Role.class;
    }
}
