package com.valentinNikolaev.jsonCrud.repository;

import com.valentinNikolaev.jsonCrud.dao.UserDao;
import com.valentinNikolaev.jsonCrud.models.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public User add(User user) {
        return userDao.add(user);
    }

    public User get(long userId) {
        return userDao.get(userId);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public User change(User user) {
        return userDao.change(user);
    }

    public boolean remove(long userId) {
        return userDao.remove(userId);
    }

    public boolean removeAll() {
        return userDao.removeAll();
    }

    public boolean isContains(long userId) {
        return userDao.isContains(userId);
    }
}
