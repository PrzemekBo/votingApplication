package com.voting.voting.services.impl;

import com.voting.voting.entity.Object;
import com.voting.voting.repositories.ObjectRepository;
import com.voting.voting.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ObjectServiceImpl implements ObjectService {

    private ObjectRepository objectRepository;

    @Override
    public Object findBookById(Long id) {
        return objectRepository.getOne(id);
    }

    @Autowired
    public ObjectServiceImpl(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Override
    public List<Object> findAllObjects() {
        return objectRepository.findAll();
    }

    @Override
    public List<Object> findAllAndSort() {
        return objectRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    @Override
    public Object saveObject(Object object) {
        //     object.setName(object.getName());
        return objectRepository.save(object);
    }

    @Override
    public void editObject(Object object, Long id) {
        Object editedObject = objectRepository.getOne(id);
        editedObject.setVotes(object.getVotes() + 1);
        objectRepository.save(editedObject);
    }
}