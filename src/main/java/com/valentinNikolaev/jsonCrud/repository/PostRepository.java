package com.valentinNikolaev.jsonCrud.repository;

import com.valentinNikolaev.jsonCrud.models.Post;

import java.util.List;

public interface PostRepository extends GenericDao<Post,Long> {

    List<Post> getPostsByUserId(Long userId);

    boolean removePostsByUserId(Long userId);


}
