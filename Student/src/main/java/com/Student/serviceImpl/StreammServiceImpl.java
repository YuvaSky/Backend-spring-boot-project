package com.Student.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.Dto.StreammDto;
import com.Student.model.Streamm;
import com.Student.model.StudentDetails;
import com.Student.repository.StreammRepository;
import com.Student.repository.StudentDetailsRepository;
import com.Student.service.StreammService;

@Service
public class StreammServiceImpl implements StreammService {
	
	@Autowired
	private StreammRepository streammRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;

	@Override
	public StreammDto createStreamm(StreammDto streammDto,Integer rollId) {
		StudentDetails details = this.studentDetailsRepository.findById(rollId).orElseThrow();
		Streamm streamm = this.modelMapper.map(streammDto, Streamm.class);
		streamm.setStudentDetails(details);
		Streamm save = this.streammRepository.save(streamm);
		StreammDto dto = this.modelMapper.map(save, StreammDto.class);		
		return dto;
	}

	@Override
	public StreammDto updateStreamm(StreammDto streammDto, Integer sid) {
		Streamm upstream = this.streammRepository.findById(sid).orElseThrow();
		upstream.setStream(streammDto.getStream());
		Streamm update = this.streammRepository.save(upstream);
		StreammDto up = this.modelMapper.map(update, StreammDto.class);
		return up;
	}

}
