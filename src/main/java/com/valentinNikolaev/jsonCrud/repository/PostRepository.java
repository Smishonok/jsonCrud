package com.valentinNikolaev.jsonCrud.repository;

import com.valentinNikolaev.jsonCrud.dao.PostDao;
import com.valentinNikolaev.jsonCrud.models.Post;

import java.util.List;

public class PostRepository {

    private PostDao postDao;

    public PostRepository(PostDao postDao) {
        this.postDao = postDao;
    }

    public Post add(Post post) {
        return postDao.add(post);
    }

    public Post get(long id) {
        return postDao.get(id);
    }

    public List<Post> getAll() {
        return postDao.getAll();
    }

    public List<Post> getPostsByUserId(long id) {
        return postDao.getPostsByUserId(id);
    }

    public Post change(Post post) {
        return postDao.change(post);
    }

    public boolean remove(long id) {
        return postDao.remove(id);

    }

    public boolean removePostsByUserId(long id) {
        return postDao.removePostsByUserId(id);
    }

    public boolean removeAll() {
        return postDao.removeAll();
    }

    public boolean isContains(long id) {
        return postDao.isContains(id);
    }
}
