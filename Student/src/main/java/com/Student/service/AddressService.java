package com.Student.service;

import org.springframework.stereotype.Repository;

import com.Student.Dto.AddressDto;

@Repository
public interface AddressService {
	
	AddressDto createaddress(AddressDto addressDto);
	AddressDto updateaddress(AddressDto addressDto,Integer addressId);

}
