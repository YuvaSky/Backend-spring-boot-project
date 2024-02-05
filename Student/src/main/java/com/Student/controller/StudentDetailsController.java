package com.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Dto.ApiResponse;
import com.Student.Dto.PostResponse;
import com.Student.Dto.StudentDetailsDto;
import com.Student.config.AppConstant;
import com.Student.service.StudentDetailService;

@RestController
@RequestMapping("/api/std")
public class StudentDetailsController {
	
	@Autowired
	private StudentDetailService studentDetailService;
	
	@PostMapping("/student/{studentId}/address/{addressId}/studentDetails")
	public ResponseEntity<StudentDetailsDto> createstudentDetails(@RequestBody StudentDetailsDto studentDetailsDto, 
			@PathVariable("studentId") Integer studentId,@PathVariable("addressId") Integer addressId,Integer sid){
		StudentDetailsDto createstudentdetail = this.studentDetailService.createStudentDetail(studentDetailsDto, studentId, addressId,sid);
		return  new ResponseEntity<StudentDetailsDto>(createstudentdetail,HttpStatus.CREATED);
	}
	@PutMapping("/studentDetails/{rollid}")
	public ResponseEntity<StudentDetailsDto> updatestudentDetails(@RequestBody StudentDetailsDto studentDetailsDto,@PathVariable("rollid") Integer rollId){
		StudentDetailsDto updatestudentDetail = this.studentDetailService.updatestudentDetail(studentDetailsDto, rollId);
		return new ResponseEntity<StudentDetailsDto>(updatestudentDetail,HttpStatus.OK);
	}
	@DeleteMapping("/studentDetails/{rollid}")
	 public ResponseEntity<ApiResponse>  deletestudent(@PathVariable("rollid") Integer rollId){
    	this.studentDetailService.deleteStudentDetails(rollId);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true), HttpStatus.OK);
    }
	@GetMapping("/studentDetails/{rollid}")
	public ResponseEntity<StudentDetailsDto> getstdById(@PathVariable("rollid") Integer rollId){
		StudentDetailsDto getstdById = this.studentDetailService.getstdById(rollId);
		return new ResponseEntity<StudentDetailsDto>(getstdById,HttpStatus.OK);
	}
	@GetMapping("/studentDetails")
	public ResponseEntity<PostResponse>getAllStd(
			@RequestParam(value = "pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue =AppConstant.SORT_BY,required = false)String sortBy){
		 PostResponse postResponse =  this.studentDetailService.getAllStd(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("/student/{studentId}/studentDetails")
	public ResponseEntity<List<StudentDetailsDto>>getBystudent(@PathVariable("studentId") Integer studentId){
		List<StudentDetailsDto> getstdBystudent = this.studentDetailService.getstdBystudent(studentId);
		return new ResponseEntity<List<StudentDetailsDto>>(getstdBystudent,HttpStatus.OK);
	}
	@GetMapping("/address/{addressId}/studentDetails")
	public ResponseEntity<List<StudentDetailsDto>>getByaddress(@PathVariable("addressId") Integer addressId){
		List<StudentDetailsDto> getstdByaddress = this.studentDetailService.getstdByaddress(addressId);
		return new ResponseEntity<List<StudentDetailsDto>>(getstdByaddress,HttpStatus.OK);
	}

}
