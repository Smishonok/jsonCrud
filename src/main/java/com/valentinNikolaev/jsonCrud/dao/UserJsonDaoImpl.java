package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.User;
import com.valentinNikolaev.jsonCrud.service.jsonParser.JsonParser;
import com.valentinNikolaev.jsonCrud.utils.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserJsonDaoImpl implements UserDao {

    private JsonParser<User> parser;
    private Path usersRepositoryPath = Constants.REPOSITORY_PATH.resolve(
            Constants.USER_REPOSITORY_FILE_NAME);

    public UserJsonDaoImpl(JsonParser<User> parser) {
        this.parser = parser;
        FileService.createUserRepository(usersRepositoryPath);
    }

    @Override
    public User add(User entity) {
        String repositoryData = FileService.getDataFromRepository(usersRepositoryPath);
        List<User> users = parser.parseList(repositoryData) == null
                           ? new ArrayList<>()
                           : parser.parseList(repositoryData);
        users.add(entity);

        String dataForWritingInRepo = parser.serialise(users);
        FileService.writeDataIntoRepository(dataForWritingInRepo, usersRepositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(usersRepositoryPath))
                .stream()
                .filter(user->user.equals(entity))
                .findFirst()
                .get();
    }

    @Override
    public User get(Long aLong) {
        return parser
                .parseList(FileService.getDataFromRepository(usersRepositoryPath))
                .stream()
                .filter(user->user.getId() == aLong)
                .findFirst()
                .get();
    }

    @Override
    public User change(User entity) {
        String repositoryData = FileService.getDataFromRepository(usersRepositoryPath);
        List<User> users = parser.parseList(repositoryData);

        users.stream().filter(user->user.getId() == entity.getId()).forEach(users::remove);
        users.add(entity);

        String dataForWritingInRepo = parser.serialise(users);
        FileService.writeDataIntoRepository(dataForWritingInRepo, usersRepositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(usersRepositoryPath))
                .stream()
                .filter(user->user.getId() == entity.getId())
                .findFirst()
                .get();
    }

    @Override
    public boolean remove(Long aLong) {
        String repositoryData = FileService.getDataFromRepository(usersRepositoryPath);
        List<User> users = parser.parseList(repositoryData);

        users.stream().filter(user->user.getId() == aLong).forEach(users::remove);
        return parser
                .parseList(FileService.getDataFromRepository(usersRepositoryPath))
                .stream()
                .noneMatch((user->user.getId() == aLong));
    }

    @Override
    public List<User> getAll() {
        return parser.parseList(FileService.getDataFromRepository(usersRepositoryPath));
    }

    @Override
    public boolean removeAll() {
        String dataForWritingInRepo = parser.serialise(new ArrayList<>());
        FileService.writeDataIntoRepository(dataForWritingInRepo, usersRepositoryPath);
        return parser.parseList(FileService.getDataFromRepository(usersRepositoryPath)).isEmpty();
    }

    @Override
    public boolean isContains(Long aLong) {
        return parser
                .parseList(FileService.getDataFromRepository(usersRepositoryPath))
                .stream()
                .anyMatch(user->user.getId() == aLong);
    }
}