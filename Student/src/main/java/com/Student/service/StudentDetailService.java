package com.Student.service;

import java.util.List;

import com.Student.Dto.PostResponse;
import com.Student.Dto.StudentDetailsDto;

public interface StudentDetailService {
	
		//create
		StudentDetailsDto createStudentDetail(StudentDetailsDto studentDetailDto, Integer studentId, Integer addressId,Integer sid);
		//update
		StudentDetailsDto updatestudentDetail(StudentDetailsDto studentDetailDto, Integer rollId);
		//delete
		void deleteStudentDetails(Integer rollId);
		//get all post
		PostResponse getAllStd(Integer pageNumber,Integer pageSize,String sortBy);
		
		//get std by id
		StudentDetailsDto getstdById(Integer rollId);
		//get all std by address
		List<StudentDetailsDto> getstdByaddress(Integer addressId);
		
		//get all std by student
		List<StudentDetailsDto> getstdBystudent(Integer studentId);
		
		//search posts
		//List<PostDto> search(String keyword);

	


}
