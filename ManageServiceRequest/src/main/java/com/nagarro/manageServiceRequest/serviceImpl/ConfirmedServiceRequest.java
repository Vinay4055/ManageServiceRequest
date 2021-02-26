package com.nagarro.manageServiceRequest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;
import com.nagarro.manageServiceRequest.entity.ServiceRequest;
import com.nagarro.manageServiceRequest.service.CancelServiceRequest;
import com.nagarro.manageServiceRequest.service.ManageServiceRequest;
@Service
public class ConfirmedServiceRequest implements CancelServiceRequest{
	@Autowired
	ManageServiceRequest manageServiceRequest;
	@Autowired
	JmsTemplate jmsTemplate;	
	@Autowired
	Gson gson;
	@Override
	public void cancelServiceRequest(String serviceId) {
		System.out.println("Manage Service Request = "+manageServiceRequest);
		ServiceRequest serviceRequest = manageServiceRequest.findServiceRequest(serviceId);
		System.out.println("Service Request = "+serviceRequest);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CANCEL);
		jmsTemplate.convertAndSend("cancelServiceRequestFromAdmin", gson.toJson(serviceId));
		jmsTemplate.convertAndSend("CancelServiceRequestFromServiceProvider", gson.toJson(serviceId));
	}

}
