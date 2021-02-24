package com.nagarro.manageServiceRequest.service;

import org.springframework.stereotype.Service;

import com.nagarro.manageServiceRequest.entity.ServiceRequest;
@Service
public interface ManageServiceRequest {
	public String createServiceRequest(ServiceRequest serviceRequest); //returns serviceRequestId for the service,Used to track the serviceRequest
	public CancelServiceRequest getCancelRequestType(String serviceId);//returns the status of Request(PENDING,ACCEPTED,etc)
	public ServiceRequest findServiceRequest(String serviceRequestId);
}
