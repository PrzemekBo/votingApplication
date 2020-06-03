package com.voting.voting.repositories;

import com.voting.voting.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Long> {
    List<Object> findByName(String name);

}

