package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.Region;
import com.valentinNikolaev.jsonCrud.service.jsonParser.UserParser;
import org.junit.Test;
import com.valentinNikolaev.jsonCrud.models.User;

import static org.junit.Assert.*;

public class UserJsonDaoImplTest {

    private UserDao userDao = new UserJsonDaoImpl(new UserParser());

    @Test
    public void whenAddUserToRepoThenUserAddedInRepo() {
        User user = new User(1l, "Valentin", "Nikolaev", new Region(1, "Moscow"));
        userDao.add(user);
        assertEquals(user,userDao.get(user.getId()));
    }




}