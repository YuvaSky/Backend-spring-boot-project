package com.Student.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Student.Dto.PostResponse;
import com.Student.Dto.StudentDetailsDto;
import com.Student.exception.ResourceNotFoundException;
import com.Student.model.Address;
import com.Student.model.Streamm;
import com.Student.model.Student;
import com.Student.model.StudentDetails;
import com.Student.repository.AddressRepository;
import com.Student.repository.StreammRepository;
import com.Student.repository.StudentDetailsRepository;
import com.Student.repository.StudentRepository;
import com.Student.service.StudentDetailService;
@Service
public class StudentDetailServiceImpl implements StudentDetailService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private StreammRepository streammRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentDetailsDto createStudentDetail(StudentDetailsDto studentDetailDto, Integer studentId,
			Integer addressId,Integer sid) {
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
		Address address = this.addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("address","Id",addressId));
		Streamm stream = this.streammRepository.findById(sid).orElseThrow(()-> new ResourceNotFoundException("streamm","Id",sid));
		StudentDetails studentDetails = this.modelMapper.map(studentDetailDto, StudentDetails.class);
		studentDetails.setImageName("default.png");
		studentDetails.setAddedDate(new Date());
		studentDetails.setStudent(student);
		studentDetails.setAddress(address);
		studentDetails.setStreamm(stream);
		StudentDetails std = this.studentDetailsRepository.save(studentDetails);
		StudentDetailsDto detailsDto = this.modelMapper.map(std, StudentDetailsDto.class);
		return detailsDto;
	}

	@Override
	public StudentDetailsDto updatestudentDetail(StudentDetailsDto studentDetailDto, Integer rollId) {
		StudentDetails update = this.studentDetailsRepository.findById(rollId).orElseThrow(()-> new ResourceNotFoundException("std","Id",rollId));
		update.setSubject(studentDetailDto.getSubject());
		update.setSection(studentDetailDto.getSection());
		StudentDetails updatedetails = this.studentDetailsRepository.save(update);
		StudentDetailsDto updateDto = this.modelMapper.map(updatedetails, StudentDetailsDto.class);
		return updateDto;
	}

	@Override
	public void deleteStudentDetails(Integer rollId) {
		StudentDetails del = this.studentDetailsRepository.findById(rollId).orElseThrow(()-> new ResourceNotFoundException("std","Id",rollId));
         this.studentDetailsRepository.delete(del);
	}

	@Override
	public StudentDetailsDto getstdById(Integer rollId) {
		StudentDetails getId = this.studentDetailsRepository.findById(rollId).orElseThrow(()-> new ResourceNotFoundException("std","Id",rollId));
		StudentDetailsDto dto = this.modelMapper.map(getId,StudentDetailsDto.class );
		return dto;
	}
	@Override
	public PostResponse getAllStd(Integer pageNumber,Integer pageSize,String sortBy) {
		PageRequest p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		  Page<StudentDetails> pageStd = this.studentDetailsRepository.findAll(p);
		 List<StudentDetails> findAll = pageStd.getContent();
		 List<StudentDetailsDto> All = findAll.stream().map(std->this.modelMapper.map(std, StudentDetailsDto.class)).collect(Collectors.toList());
		 PostResponse postResponse = new PostResponse();
		 postResponse.setContent(All);
		 postResponse.setPageNumber(pageStd.getNumber());
		 postResponse.setPageSize(pageStd.getSize());
		 postResponse.setTotalEelements(pageStd.getTotalElements());
		 postResponse.setTotalPages(pageStd.getTotalPages());
		 postResponse.setLastPage(pageStd.isLast());
		 return  postResponse;
	}

	@Override
	public List<StudentDetailsDto> getstdByaddress(Integer addressId) {
		Address address = this.addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("address","Id",addressId));
		List<StudentDetails> byaddress = this.studentDetailsRepository.findByaddress(address);
		List<StudentDetailsDto> address1 = byaddress.stream().map(add->this.modelMapper.map(add,StudentDetailsDto.class)).collect(Collectors.toList());
		return address1;
	}

	@Override
	public List<StudentDetailsDto> getstdBystudent(Integer studentId) {
		Student std = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("address","Id",studentId));
		List<StudentDetails> bystudent = this.studentDetailsRepository.findBystudent(std);
		List<StudentDetailsDto> student1 = bystudent.stream().map(add->this.modelMapper.map(add,StudentDetailsDto.class)).collect(Collectors.toList());
		return student1;
	}

}
