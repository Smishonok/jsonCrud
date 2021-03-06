package com.valentinNikolaev.jsonCrud.repository.jsonImpl;

import com.valentinNikolaev.jsonCrud.models.Post;
import com.valentinNikolaev.jsonCrud.repository.PostRepository;
import com.valentinNikolaev.jsonCrud.service.jsonParser.JsonParser;
import com.valentinNikolaev.jsonCrud.service.jsonParser.JsonParserFactory;
import com.valentinNikolaev.jsonCrud.utils.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepositoryImpl implements PostRepository {

    private JsonParser<Post> parser         = JsonParserFactory.getFactory(Post.class).getParser();
    private Path             repositoryPath = Constants.REPOSITORY_PATH.resolve(
            Constants.POST_REPOSITORY_FILE_NAME);

    {
        FileService.createRepository(repositoryPath);
    }

    @Override
    public Post add(Post entity) {
        String repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Post> posts = parser.parseList(repositoryData) == null ? new ArrayList<>() :
                parser.parseList(repositoryData);
        posts.add(entity);

        String dataForWritingInRepo = parser.serialise(posts);
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);

        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).stream().filter(
                post->post.equals(entity)).findFirst().get();
    }

    @Override
    public Post get(Long aLong) {
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).stream().filter(
                post->post.getId() == aLong).findFirst().get();
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).stream().filter(
                post->post.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Post change(Post entity) {
        String     repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Post> posts          = parser.parseList(repositoryData);

        posts
                .stream()
                .filter(post->post.getId() == entity.getId())
                .collect(Collectors.toList())
                .forEach(posts::remove);
        posts.add(entity);

        String dataForWritingInRepo = parser.serialise(posts);
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);

        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).stream().filter(
                post->post.getId() == entity.getId()).findFirst().get();
    }

    @Override
    public boolean remove(Long aLong) {
        String     repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Post> posts          = parser.parseList(repositoryData);

        posts.stream().filter(post->post.getId() == aLong).collect(Collectors.toList()).forEach(
                posts::remove);
        FileService.writeDataIntoRepository(parser.serialise(posts), repositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .noneMatch((post->post.getId() == aLong));
    }

    @Override
    public boolean removePostsByUserId(Long userId) {
        String     repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Post> posts          = parser.parseList(repositoryData);

        posts
                .stream()
                .filter(post->post.getUserId() == userId)
                .collect(Collectors.toList())
                .forEach(posts::remove);
        FileService.writeDataIntoRepository(parser.serialise(posts), repositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .noneMatch((post->post.getUserId() == userId));
    }

    @Override
    public boolean removeAll() {
        String dataForWritingInRepo = parser.serialise(new ArrayList<>());
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).isEmpty();
    }

    @Override
    public List<Post> getAll() {
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)) == null ?
                new ArrayList<>() : parser.parseList(
                FileService.getDataFromRepository(repositoryPath));
    }

    @Override
    public boolean isContains(Long aLong) {
        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .anyMatch(post->post.getId() == aLong);
    }
}
