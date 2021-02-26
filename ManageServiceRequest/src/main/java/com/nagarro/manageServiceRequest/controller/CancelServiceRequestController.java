package com.nagarro.manageServiceRequest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.manageServiceRequest.service.CancelServiceRequest;
import com.nagarro.manageServiceRequest.service.ManageServiceRequest;

@RestController
@RequestMapping("/cancelServiceRequest")
public class CancelServiceRequestController {
	@Autowired
	ManageServiceRequest manageServiceRequest;
	public CancelServiceRequest cancelServiceRequest;

	@PutMapping("/{serviceId}")
	public ResponseEntity<Void> cancelServiceRequest(@PathVariable(name = "serviceId") String serviceRequestId) {
			this.cancelServiceRequest = this.manageServiceRequest.getCancelRequestType(serviceRequestId);
			if(this.cancelServiceRequest != null) {
			this.cancelServiceRequest.cancelServiceRequest(serviceRequestId);
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
			}
			else
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
