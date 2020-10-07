package com.valentinNikolaev.jsonCrud.controller;

import com.valentinNikolaev.jsonCrud.models.Region;

import java.util.List;
import java.util.Optional;

public interface RegionController {

    Region addRegion(String name);

    Optional<Region> getRegionById(String regionId);

    Optional<Region> getRegionByName(String regionName);

    boolean changeRegionName(String regionId, String newRegionName);

    boolean removeRegionWithId(String regionId);

    boolean removeAllRegions();

    List<Region> getAllRegions();
}
