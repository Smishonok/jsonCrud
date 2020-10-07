package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.Post;

import java.util.List;

public interface PostDao extends GenericDao<Post,Long> {

    List<Post> getPostsByUserId(Long userId);

    boolean removePostsByUserId(Long userId);


}
