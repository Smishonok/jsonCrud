package com.valentinNikolaev.jsonCrud.view.postsRequestsHandlers;

import com.valentinNikolaev.jsonCrud.controller.*;
import com.valentinNikolaev.jsonCrud.models.User;
import com.valentinNikolaev.jsonCrud.view.RequestHandler;

import java.util.List;
import java.util.Optional;

public class AddPostRequestHandler extends PostRequestHandler {

    private PostController postController;
    private UserController userController;

    public AddPostRequestHandler() {
    }

    public AddPostRequestHandler(RequestHandler nextRequestHandler) {
        super(nextRequestHandler);
    }

    @Override
    public void handleRequest(String action, List<String> options) throws ClassNotFoundException {
        if (ADD.equals(action)) {
            this.postController = ControllersIocContainer.getPostController();
            this.userController = ControllersIocContainer.getUserController();
            processRequest(options);
        } else {
            getNextHandler(action, options);
        }
    }

    private void processRequest(List<String> options) {
        if (options.size() == 0) {
            getErrorMessage();
            return;
        }

        String requestType = options.get(0);

        if (requestType.equals(HELP)) {
            getHelpForAddingPostRequest();
            return;
        }

        addPost(options);
    }

    private void addPost(List<String> requestOptions) {
        if (checkRequestOptions(requestOptions)) {
            String userIdValue = requestOptions.get(0);
            String content = getContent(requestOptions);
            this.postController.addPost(userIdValue, content);
        }
    }

    private String getContent(List<String> requestOptions) {
        StringBuilder postContent = new StringBuilder();
        if (requestOptions.size() > 1) {
            for (int i = 1; i < requestOptions.size(); i++) {
                postContent.append(" ").append(requestOptions.get(i));
            }
        }
        return postContent.toString();
    }

    private boolean checkRequestOptions(List<String> requestOptions) {
        boolean isOptionsCorrect = true;
        if (requestOptions.size() == 1) {
            System.out.println(
                    "Invalid request format. Please, check request format and try again, " +
                    "or get help information.");
            isOptionsCorrect = false;
        }

        String userIdValue = requestOptions.get(0);
        if (! isLong(userIdValue)) {
            System.out.println("The user`s id should consist only of numbers. Please, check the " +
                               "user`s id and try again.");
            isOptionsCorrect = false;
        }

        if (! isUserExists(userIdValue)) {
            System.out.println(
                    "User with id: " + userIdValue + " is not exists. Please, check the " +
                    "user`s id number and try again.\n");
            isOptionsCorrect = false;
        }
        return isOptionsCorrect;
    }

    private void getErrorMessage() {
        System.out.println("Invalid request type. Please, check request type and try " +
                           "again, or take help information using \"" + ADD + " " + HELP + "\".\n");
    }

    private boolean isUserExists(String userId) {
        Optional<User> user = this.userController.getUserById(userId);
        return user.isPresent();
    }

    private void getHelpForAddingPostRequest() {
        String helpInfo = "For adding posts into the repository it can be used next formats of" +
                          "request:\n" + "\t" + ADD + " " + USER_ID + "[user id]" + " [content]";
        System.out.println(helpInfo);
    }
}
