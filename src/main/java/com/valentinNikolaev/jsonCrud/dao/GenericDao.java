package com.valentinNikolaev.jsonCrud.dao;

import java.util.List;

public interface GenericDao<T,ID> {

    T add(T entity);

    T get(ID id);

    T change(T entity);

    boolean remove(ID id);

    List<T> getAll();

    boolean removeAll();

    boolean isContains(ID id);

}
