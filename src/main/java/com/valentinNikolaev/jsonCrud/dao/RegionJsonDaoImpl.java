package com.valentinNikolaev.jsonCrud.dao;

import com.valentinNikolaev.jsonCrud.models.Region;
import com.valentinNikolaev.jsonCrud.service.jsonParser.JsonParser;
import com.valentinNikolaev.jsonCrud.service.jsonParser.JsonParserFactory;
import com.valentinNikolaev.jsonCrud.utils.Constants;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RegionJsonDaoImpl implements RegionDao {

    private JsonParser<Region> parser = JsonParserFactory.getFactory(Region.class).getParser();
    private Path repositoryPath = Constants.REPOSITORY_PATH.resolve(
            Constants.REGION_REPOSITORY_FILE_NAME);

    {
        FileService.createRepository(repositoryPath);
    }

    @Override
    public Region add(Region entity) {
        String repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Region> posts = parser.parseList(repositoryData) == null
                             ? new ArrayList<>()
                             : parser.parseList(repositoryData);
        posts.add(entity);

        String dataForWritingInRepo = parser.serialise(posts);
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .filter(region->region.equals(entity))
                .findFirst()
                .get();
    }

    @Override
    public Region get(Long aLong) {
        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .filter(region->region.getId() == aLong)
                .findFirst()
                .get();
    }

    @Override
    public Region change(Region entity) {
        String repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Region> posts = parser.parseList(repositoryData);

        posts.stream().filter(region->region.getId() == entity.getId()).forEach(posts::remove);
        posts.add(entity);

        String dataForWritingInRepo = parser.serialise(posts);
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);

        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .filter(region->region.getId() == entity.getId())
                .findFirst()
                .get();
    }

    @Override
    public boolean remove(Long aLong) {
        String repositoryData = FileService.getDataFromRepository(repositoryPath);
        List<Region> posts = parser.parseList(repositoryData);

        posts.stream().filter(region->region.getId() == aLong).forEach(posts::remove);
        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .noneMatch((region->region.getId() == aLong));
    }

    @Override
    public List<Region> getAll() {
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)) == null
               ? new ArrayList<>()
               : parser.parseList(FileService.getDataFromRepository(repositoryPath));
    }

    @Override
    public boolean removeAll() {
        String dataForWritingInRepo = parser.serialise(new ArrayList<>());
        FileService.writeDataIntoRepository(dataForWritingInRepo, repositoryPath);
        return parser.parseList(FileService.getDataFromRepository(repositoryPath)).isEmpty();
    }

    @Override
    public boolean isContains(Long aLong) {
        return parser
                .parseList(FileService.getDataFromRepository(repositoryPath))
                .stream()
                .anyMatch(region->region.getId() == aLong);
    }
}
