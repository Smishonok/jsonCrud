package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.Post;

import java.util.List;

public class PostJsonDaoImpl implements PostDao {

    //Post`s fields names for parsing
    private final String POST_ID       = "Post`s id:";
    private final String USER_ID       = "User`s id:";
    private final String CREATION_DATE = "Post`s creation date:";
    private final String UPDATING_DATE = "Post`s updating date:";
    private final String POST_CONTENT  = "Post`s content:";



    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return null;
    }

    @Override
    public boolean removePostsByUserId(Long userId) {
        return false;
    }

    @Override
    public Post add(Post entity) {
        return null;
    }

    @Override
    public Post get(Long aLong) {
        return null;
    }

    @Override
    public Post change(Post entity) {
        return null;
    }

    @Override
    public boolean remove(Long aLong) {
        return false;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public boolean removeAll() {
        return false;
    }

    @Override
    public boolean isContains(Long aLong) {
        return false;
    }
}
