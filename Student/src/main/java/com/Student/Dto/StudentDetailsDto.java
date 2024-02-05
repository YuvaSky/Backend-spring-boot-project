package com.Student.Dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StudentDetailsDto {
	
	private int rollId;
	private String subject;
	private String section;
	private String imageName;
	private Date addedDate;
	private StudentDto student;
	private AddressDto address;
	private StreammDto streammDto;

     

}
