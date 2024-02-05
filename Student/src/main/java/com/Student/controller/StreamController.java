package com.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Student.Dto.StreammDto;
import com.Student.service.StreammService;

@RestController
@RequestMapping("/api/streamm")
public class StreamController {
	
	@Autowired
	private StreammService streamService;
	
	@PostMapping("/studentDetails/{rollId}/streamm")
	public ResponseEntity<StreammDto> createstream(@RequestBody StreammDto streammDto,@PathVariable("rollId") Integer rollId){
		StreammDto createstream = this.streamService.createStreamm(streammDto,rollId);
		return new  ResponseEntity<StreammDto>(createstream,HttpStatus.CREATED);
	}
    @PutMapping("/{sid}")
    public ResponseEntity<StreammDto> updateStream(@RequestBody StreammDto streammDto,@PathVariable("sid") Integer sid){
    	StreammDto updatestream = this.streamService.updateStreamm(streammDto, sid);
    	return new ResponseEntity<StreammDto>(updatestream,HttpStatus.OK);
    }

}
