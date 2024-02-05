package com.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Student.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
