package com.valentinNikolaev.jsonCrud.controller;

import com.valentinNikolaev.jsonCrud.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostController {

    Post addPost(String userId, String content);

    Optional<Post> getPost(String postId);

    List<Post> getAllPostsList();

    List<Post> getPostsByUserId(String userId);

    boolean changePost(String postId, String newContent);

    boolean removePost(String postId);

    boolean removeAllPostByUser(String userId);

    boolean removeAllPosts();
}

