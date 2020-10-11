package com.valentinNikolaev.jsonCrud.controller;

import com.valentinNikolaev.jsonCrud.repository.jsonImpl.PostRepositoryImpl;
import com.valentinNikolaev.jsonCrud.repository.jsonImpl.RegionRepositoryImpl;
import com.valentinNikolaev.jsonCrud.repository.jsonImpl.UserRepositoryImpl;

public class ControllersIocContainer {

    private static PostControllerImpl postController;
    private static UserControllerImpl userController;
    private static RegionControllerImpl regionController;

    static {
        postController   = new PostControllerImpl(new PostRepositoryImpl());
        userController   = new UserControllerImpl(new UserRepositoryImpl());
        regionController = new RegionControllerImpl(new RegionRepositoryImpl());

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
