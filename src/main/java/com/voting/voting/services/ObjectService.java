package com.voting.voting.services;

import com.voting.voting.entity.Object;

import java.util.List;

public interface ObjectService {
    List<Object> findAllObjects();
    List<Object> findAllAndSort();
    Object saveObject(Object object);
    public Object findBookById(long id);
    Object update(Long id);
    void editObject(Object object, long id);
    Object findObjectById(long id);
}
