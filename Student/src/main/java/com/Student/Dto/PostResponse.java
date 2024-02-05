package com.Student.Dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<StudentDetailsDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalEelements;
	private int totalPages;
	private boolean lastPage;
	

}
