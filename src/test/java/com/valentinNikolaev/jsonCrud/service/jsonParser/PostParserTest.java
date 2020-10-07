package com.valentinNikolaev.jsonCrud.service.jsonParser;

import com.valentinNikolaev.jsonCrud.models.Post;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PostParserTest {

    private PostParser parser = new PostParser();

    @Test
    public void whenSerializeObjectThenReturnStringWithSerialisedObject() {
        Post post = new Post(1l, 1l, "Test post",
                             LocalDateTime.ofEpochSecond(1765425548L, 0, ZoneOffset.UTC),
                             LocalDateTime.ofEpochSecond(1765425548L, 0, ZoneOffset.UTC));
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        String serialisedPosts = parser.serialise(posts);
        String postInJson = "[{\"id\":1,\"userId\":1,\"content\":\"Test post\",\"created\":{\"date\":{\"year\":2025,\"month\":12,\"day\":11},\"time\":{\"hour\":3,\"minute\":59,\"second\":8,\"nano\":0}},\"updated\":{\"date\":{\"year\":2025,\"month\":12,\"day\":11},\"time\":{\"hour\":3,\"minute\":59,\"second\":8,\"nano\":0}}}]";
        assertEquals(postInJson, serialisedPosts);
    }

    @Test
    public void whenDeserializeObjectFromJsonThenReturnObject() {
        Post post = new Post(1l, 1l, "Test post",
                             LocalDateTime.ofEpochSecond(1765425548L, 0, ZoneOffset.UTC),
                             LocalDateTime.ofEpochSecond(1765425548L, 0, ZoneOffset.UTC));
        List<Post> postsBeforeSerializing = new ArrayList<>();
        postsBeforeSerializing.add(post);

        String postInJson = "[{\"id\":1,\"userId\":1,\"content\":\"Test post\",\"created\":{\"date\":{\"year\":2025,\"month\":12,\"day\":11},\"time\":{\"hour\":3,\"minute\":59,\"second\":8,\"nano\":0}},\"updated\":{\"date\":{\"year\":2025,\"month\":12,\"day\":11},\"time\":{\"hour\":3,\"minute\":59,\"second\":8,\"nano\":0}}}]";
        List<Post> postsAfterDeserializing = parser.parseList(postInJson);
        assertEquals(postsBeforeSerializing, postsAfterDeserializing);
    }


}