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

import com.Student.Dto.AddressDto;
import com.Student.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/create")
	public ResponseEntity<AddressDto> createaddress(@RequestBody AddressDto addressDto){
		AddressDto createaddress = this.addressService.createaddress(addressDto);
		return new ResponseEntity<>(createaddress,HttpStatus.OK);		
	}
	
	@PutMapping("/{addressid}")
	public ResponseEntity<AddressDto> updateaddress(@RequestBody AddressDto addressDto,
			@PathVariable("addressid")Integer addressId ){
		AddressDto updateaddress = this.addressService.updateaddress(addressDto, addressId);
		return new ResponseEntity<>(updateaddress,HttpStatus.OK);
	}

}
