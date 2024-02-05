package com.Student.serviceImpl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Student.Dto.AddressDto;
import com.Student.exception.ResourceNotFoundException;
import com.Student.model.Address;
import com.Student.repository.AddressRepository;
import com.Student.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;
	@Override
	public AddressDto createaddress(AddressDto addressDto) {
		Address address = this.modelMapper.map(addressDto, Address.class);
		Address save = this.addressRepository.save(address);
		AddressDto dto = this.modelMapper.map(save, AddressDto.class);
		return dto;
	}

	@Override
	public AddressDto updateaddress(AddressDto addressDto,Integer addressId) {
		Address findById = this.addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",addressId));;
		findById.setStreet(addressDto.getStreet());
		findById.setState(addressDto.getState());
		Address save = this.addressRepository.save(findById);
		AddressDto updateaddress = this.modelMapper.map(save, AddressDto.class);
		return updateaddress;
	}

}
