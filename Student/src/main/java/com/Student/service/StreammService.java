package com.Student.service;

import com.Student.Dto.StreammDto;

public interface StreammService {

	StreammDto createStreamm(StreammDto streammDto,Integer rollId);
	StreammDto updateStreamm(StreammDto streammDto, Integer sid);
}
