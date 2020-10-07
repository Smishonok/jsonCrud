package com.valentinNikolaev.jsonCrud.repository;

import com.valentinNikolaev.jsonCrud.dao.RegionDao;
import com.valentinNikolaev.jsonCrud.models.Region;

import java.util.List;

public class RegionRepository {

    private RegionDao regionDao;

    public RegionRepository(RegionDao regionDao) {
        this.regionDao = regionDao;
    }

    public Region add(Region region) {
        return regionDao.add(region);
    }

    public Region get(long id) {
        return regionDao.get(id);
    }

    public List<Region> getAll() {
        return regionDao.getAll();
    }

    public Region change(Region region) {
        return regionDao.change(region);
    }

    public boolean remove(long id) {
        return regionDao.remove(id);
    }

    public boolean removeAll() {
        return regionDao.removeAll();
    }

    public boolean isContains(long id) {
        return regionDao.isContains(id);
    }
}
