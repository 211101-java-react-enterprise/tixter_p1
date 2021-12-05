package com.revature.tixter.daos;

import java.util.List;

public interface CrudDAO<T> {
    T save(T newObj);
    List<T> findAll();
    T findById(String id) throws IllegalAccessException, InstantiationException;
    boolean update(T updatedObj);
    boolean removeById(String id);
}
