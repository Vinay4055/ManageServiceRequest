package com.nagarro.manageServiceRequest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.manageServiceRequest.mapper.Mapper;
import com.nagarro.manageServiceRequest.model.ServiceRequest;
import com.nagarro.manageServiceRequest.service.ManageServiceRequest;

@RestController
@RequestMapping("/manageServiceRequest")
public class ServiceRequestController {
	@Autowired
	ManageServiceRequest manageServiceRequest;
	@Autowired
	Mapper mapper;

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {

		manageServiceRequest.createServiceRequest(mapper.convertServiceRequestModelToEntity(serviceRequest));
		return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}

}
