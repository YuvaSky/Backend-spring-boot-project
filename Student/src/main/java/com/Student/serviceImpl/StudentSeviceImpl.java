package com.Student.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import com.Student.exception.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Student.Dto.StudentDto;
import com.Student.config.AppConstant;
import com.Student.model.Role;
import com.Student.model.Student;
import com.Student.repository.RoleRepository;
import com.Student.repository.StudentRepository;
import com.Student.service.StudentService;


@Service
public class StudentSeviceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public StudentDto createstudent(StudentDto studentDto) {
		Student student = this.modelMapper.map(studentDto, Student.class);
		Student save = this.studentRepository.save(student);
		StudentDto dto = this.modelMapper.map(save, StudentDto.class);
		
		return dto;
	}

	@Override
	public StudentDto updatestudent(StudentDto studentDto, Integer studentId) {
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
		student.setName(studentDto.getName());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		student.setAbout(studentDto.getAbout());
		Student updatestudent = this.studentRepository.save(student);
		StudentDto updateSt = this.modelMapper.map(updatestudent, StudentDto.class);
		return updateSt;
	}

	@Override
	public StudentDto getstudentById(Integer studentId) {
       Student findById = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
       StudentDto student = this.modelMapper.map(findById, StudentDto.class);
		return student;
	}

	@Override
	public void deletestudent(Integer studentId) {
		Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        this.studentRepository.delete(student);
	}

	@Override
	public List<StudentDto> getAllstudent() {
		List<Student> stud = this.studentRepository.findAll();
		List<StudentDto> studentDtos = stud.stream().map(student->this.studentTodto(student)).collect(Collectors.toList());
		return studentDtos;
	}
	private StudentDto studentTodto(Student student) {
		StudentDto studentDto = this.modelMapper.map(student, StudentDto.class);		
		return studentDto;
	}

	@Override
	public StudentDto registerNewStudent(StudentDto studentDto) {
		Student student = this.modelMapper.map(studentDto, Student.class);
		student.setPassword(this.passwordEncoder.encode(student.getPassword()));
		Role role = this.roleRepository.findById(AppConstant.NORMAL_USER).get();
		student.getRoles().add(role);
		Student save = this.studentRepository.save(student);
		StudentDto studentDto2 = this.modelMapper.map(save, StudentDto.class);
		return studentDto2;
	}
	
}
