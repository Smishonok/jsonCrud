package com.valentinNikolaev.jsonCrud.repository.jsonImpl;

import com.valentinNikolaev.jsonCrud.models.Region;
import com.valentinNikolaev.jsonCrud.models.User;
import com.valentinNikolaev.jsonCrud.repository.UserRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepositoryImplTest {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Test
    public void whenAddUserToRepoThenUserAddedInRepo() {
        User user = new User(1l, "Valentin", "Nikolaev", new Region(1, "Moscow"));
        userRepository.add(user);
        assertEquals(user, userRepository.get(user.getId()));
    }




}