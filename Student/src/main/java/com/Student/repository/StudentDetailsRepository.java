package com.Student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Student.model.Address;
import com.Student.model.Student;
import com.Student.model.StudentDetails;
@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer> {
	List<StudentDetails> findByaddress(Address address);
	List<StudentDetails> findBystudent(Student student);

}
