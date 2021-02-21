package com.nagarro.manageServiceRequest.service;

import org.springframework.stereotype.Service;

import com.nagarro.manageServiceRequest.entity.ServiceRequest;
@Service
public interface ManageServiceRequest {
	public String createServiceRequest(ServiceRequest serviceRequest); //returns serviceId for the service
	public CancelServiceRequest getCancelRequestType(String serviceId);
	public ServiceRequest findService(String serviceId);
}
