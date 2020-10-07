package com.valentinNikolaev.jsonCrud.controller;

import com.valentinNikolaev.jsonCrud.dao.PostJsonDaoImpl;
import com.valentinNikolaev.jsonCrud.dao.RegionJsonDaoImpl;
import com.valentinNikolaev.jsonCrud.dao.UserJsonDaoImpl;
import com.valentinNikolaev.jsonCrud.repository.PostRepository;
import com.valentinNikolaev.jsonCrud.repository.RegionRepository;
import com.valentinNikolaev.jsonCrud.repository.UserRepository;

public class ControllersIocContainer {

    private static PostControllerImpl postController;
    private static UserControllerImpl userController;
    private static RegionControllerImpl regionController;

    static {
        postController   = new PostControllerImpl(new PostRepository(new PostJsonDaoImpl()));
        userController   = new UserControllerImpl(new UserRepository(new UserJsonDaoImpl()));
        regionController = new RegionControllerImpl(new RegionRepository(new RegionJsonDaoImpl()));

        postController.setUserController(userController);
        userController.setRegionController(regionController);
    }

    public static PostController getPostController() {
        return postController;
    }

    public static UserController getUserController() {
        return userController;
    }

    public static RegionController getRegionController() {
        return regionController;
    }
}
