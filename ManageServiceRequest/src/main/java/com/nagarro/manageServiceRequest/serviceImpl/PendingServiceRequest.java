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
public class PendingServiceRequest implements CancelServiceRequest {

	@Autowired
	ManageServiceRequest manageServiceRequest;
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	Gson gson;
	@Override
	public void cancelServiceRequest(String serviceId) {
		ServiceRequest serviceRequest = manageServiceRequest.findServiceRequest(serviceId);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CANCEL);
		jmsTemplate.convertAndSend("cancelServiceRequestFromAdmin", gson.toJson(serviceId));
		jmsTemplate.convertAndSend("CancelServiceRequestFromServiceProvider", gson.toJson(serviceId));

	}

}
