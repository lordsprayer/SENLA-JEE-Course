package com.senla.courses.service;

import com.senla.courses.dao.IUserDao;
import com.senla.courses.exception.DaoException;
import com.senla.courses.model.Role;
import com.senla.courses.model.User;
import com.senla.courses.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService extends ConstantUtil implements IUserService {

    private static final Logger log = LogManager.getLogger(UserService.class);
    private final IUserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getAll() {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public User getById(Integer id) {
        try {
            return userDao.getByPK(id);
        } catch (DaoException e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public boolean save(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
            user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.persist(user);
            return true;
    }

    @Override
    public void delete(Integer id) {
        try {
            User user = userDao.getByPK(id);
            userDao.delete(user);
        }  catch (DaoException e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            try {
                return userDao.findByUsername(username);
            } catch (UsernameNotFoundException e) {
                log.log(Level.WARN, "User not found");
                throw new DaoException(e);
            }
        }

}
