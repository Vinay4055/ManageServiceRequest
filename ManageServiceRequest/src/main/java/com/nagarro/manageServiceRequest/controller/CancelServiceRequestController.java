package com.nagarro.manageServiceRequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.manageServiceRequest.service.CancelServiceRequest;
import com.nagarro.manageServiceRequest.service.ManageServiceRequest;

@RestController("/serviceRequest")
public class CancelServiceRequestController {
	@Autowired
	ManageServiceRequest manageServiceRequest;
	public CancelServiceRequest cancelServiceRequest;

	@PutMapping("/cancel/{serviceId}")
	public ResponseEntity<Void> cancelServiceRequest(@PathVariable(name = "serviceId") String serviceId,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			this.cancelServiceRequest = manageServiceRequest.getCancelRequestType(serviceId);
			this.cancelServiceRequest.cancelServiceRequest(serviceId);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		}
	}
}
