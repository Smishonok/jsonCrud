package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.Region;
import com.valentinNikolaev.jsonCrud.models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserJsonDaoImplTest {

    private UserDao userDao = new UserJsonDaoImpl();

    @Test
    public void whenAddUserToRepoThenUserAddedInRepo() {
        User user = new User(1l, "Valentin", "Nikolaev", new Region(1, "Moscow"));
        userDao.add(user);
        assertEquals(user,userDao.get(user.getId()));
    }




}