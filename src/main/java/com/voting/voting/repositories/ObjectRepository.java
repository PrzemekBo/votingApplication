package com.voting.voting.repositories;

import com.voting.voting.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<Object, Integer> {

}

