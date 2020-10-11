package com.valentinNikolaev.jsonCrud.view.postsRequestsHandlers;

import com.valentinNikolaev.jsonCrud.controller.ControllersIocContainer;
import com.valentinNikolaev.jsonCrud.controller.PostController;
import com.valentinNikolaev.jsonCrud.controller.PostControllerImpl;
import com.valentinNikolaev.jsonCrud.models.Post;
import com.valentinNikolaev.jsonCrud.view.RequestHandler;
import com.valentinNikolaev.jsonCrud.view.RequestParametersProcessor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChangePostRequestHandler extends PostRequestHandler {

    private PostController postController;

    public ChangePostRequestHandler() {
    }

    public ChangePostRequestHandler(RequestHandler nextRequestHandler) {
        super(nextRequestHandler);
    }

    @Override
    public void handleRequest(String action, List<String> options) throws ClassNotFoundException {
        if (CHANGE.equals(action)) {
            this.postController = ControllersIocContainer.getPostController();
            processRequest(options);
        } else {
            getNextHandler(action, options);
        }
    }

    private void processRequest(List<String> options) {
        int optionsSize = options.size();
        if (optionsSize == 0 || optionsSize == 1 && ! options.get(0).toLowerCase().equals("help")) {
            getErrorMessage();
            return;
        }

        if (options.get(0).toLowerCase().equals("help")) {
            getHelpForChangingPostContentRequest(options);
            return;
        }

        changePost(options);
    }

    private void changePost(List<String> options) {
        Optional<Post> post = this.postController.getPost(options.get(0));
        if (post.isPresent()) {
            String changedPost = String.join(" ", getOptionsWithOutFirst(options));
            this.postController.changePost(options.get(0), changedPost);
        } else {
            System.out.println(
                    "The post with id: " + options.get(0) + " is not exists in repository." +
                            " Check post id and try again.");
        }
    }

    private void getHelpForChangingPostContentRequest(List<String> options) {
        if (options.get(0).equals(HELP)) {
            String helpInfo =
                    "For changing post content it can be used next format of request:\n" + "\t" +
                            CHANGE + " [post id] [new post content]";

            System.out.println(helpInfo);
        } else {
            getErrorMessage();
        }
    }

    private void getErrorMessage() {
        System.out.println(
                "Invalid request format. Please, check request format and try again, or get " +
                        "help information.");
    }
}
