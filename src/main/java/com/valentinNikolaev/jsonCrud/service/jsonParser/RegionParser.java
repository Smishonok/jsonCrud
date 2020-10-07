package com.valentinNikolaev.jsonCrud.service.jsonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.valentinNikolaev.jsonCrud.models.Region;

import java.lang.reflect.Type;
import java.util.List;

public class RegionParser implements JsonParser<Region> {

    private Type type = new TypeToken<List<Region>>() {}.getType();
    private Gson parser = new GsonBuilder()
            .registerTypeAdapter(type, new RegionListConverter())
            .create();

    @Override
    public List<Region> parseList(String text) {
        return null;
    }

    @Override
    public String serialise(List<Region> entities) {
        return null;
    }
}
