package com.Student.Dto;

import lombok.Data;

@Data
public class JwtAuthResponse {
	
	private String token;
	private StudentDto student;

}
