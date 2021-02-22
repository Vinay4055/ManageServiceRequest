package com.nagarro.manageServiceRequest.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.manageServiceRequest.model.ServiceRequest;

@Component
public class Mapper {
	@Autowired
	ModelMapper modelMapper;

	public ServiceRequest convertServiceRequestEntityToModel(
			com.nagarro.manageServiceRequest.entity.ServiceRequest source) {
		return modelMapper.map(source, ServiceRequest.class);
	}

	public com.nagarro.manageServiceRequest.entity.ServiceRequest convertServiceRequestModelToEntity(
			ServiceRequest source) {
		return modelMapper.map(source, com.nagarro.manageServiceRequest.entity.ServiceRequest.class);
	}

}
