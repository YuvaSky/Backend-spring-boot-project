package com.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Dto.ApiResponse;
import com.Student.Dto.StudentDto;
import com.Student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/student")
@Tag(name =  "student Controller")
public class StudentController {	
	
	@Autowired
	private StudentService studentService;
	
	
	@PostMapping("/create")
	@Operation(summary = "Post student by ID", description = "Get a student based on the provided ID")
	public ResponseEntity<StudentDto> createstudent(@RequestBody StudentDto studentDto){
		StudentDto createstudent = this.studentService.createstudent(studentDto);
		return new  ResponseEntity<>(createstudent,HttpStatus.OK);
	}
	@Operation(summary = "Put student by ID", description = "Get a student based on the provided ID")
    @PutMapping("/{studentid}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto userDto,@PathVariable("studentid") Integer studentid){
    	StudentDto updatestudent = this.studentService.updatestudent(userDto, studentid);
    	return new ResponseEntity<>(updatestudent,HttpStatus.OK);
    }
	@Operation(summary = "GEt student by ID", description = "Get a student based on the provided ID")
    @GetMapping("/{studentid}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentid") Integer studentId){
    	StudentDto getstudentById = this.studentService.getstudentById(studentId);
    	return new ResponseEntity<>(getstudentById,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{studentid}")
    public ResponseEntity<ApiResponse>  deletestudent(@PathVariable("studentid") Integer studentId){
    	this.studentService.deletestudent(studentId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true), HttpStatus.OK);
    }
    @Operation(summary = "Get student by ID", description = "Get a student based on the provided ID")
    @GetMapping("/")
    public ResponseEntity<List<StudentDto>> getAllstudent(){
    	List<StudentDto> allstudent = this.studentService.getAllstudent();
    	return new ResponseEntity<>(allstudent,HttpStatus.OK);
    }
}
