package com.Student.service;

import java.util.List;

import com.Student.Dto.StudentDto;

public interface StudentService {
	 StudentDto registerNewStudent(StudentDto studentDto);
	 StudentDto createstudent(StudentDto studentDto);
	 StudentDto updatestudent(StudentDto studentDto,Integer studentId);
	 StudentDto getstudentById(Integer studentId);
	 void deletestudent(Integer studentId);
	 List<StudentDto> getAllstudent();
		
	

}
