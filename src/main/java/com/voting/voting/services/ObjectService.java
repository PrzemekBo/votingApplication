package com.voting.voting.services;

import com.voting.voting.entity.Object;

import java.util.List;

public interface ObjectService {
    List<Object> findAllObjects();
    List<Object> findAllAndSort();
    Object saveObject(Object object);
    public Object findBookById(Long id);
    void editObject(Object object, Long id);
}
