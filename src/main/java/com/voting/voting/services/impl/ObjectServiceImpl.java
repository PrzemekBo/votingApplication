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

    public static final int MAX_SIZE = 5;

    private ObjectRepository objectRepository;

    @Override
    public Object findBookById(int id) {
        return objectRepository.getOne(id);
    }

    @Override
    public Object update(Integer id) {
        Object object = objectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        object.setVotes(object.getVotes() + 1);
        return object;
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
        List<Object> list = objectRepository.findAll();
        if (list.size() == MAX_SIZE) {
        }
        return objectRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
    }

    @Override
    public Object saveObject(Object object) {
        //     object.setName(object.getName());
        return objectRepository.save(object);
    }

    @Override
    public void editObject(Object object, int id) {
        Object editedObject = objectRepository.getOne(id);
        editedObject.setVotes(object.getVotes() + 1);
        objectRepository.save(editedObject);
    }
}