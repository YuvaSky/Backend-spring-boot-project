package com.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Student.model.Streamm;

@Repository
public interface StreammRepository extends JpaRepository<Streamm, Integer> {

}
