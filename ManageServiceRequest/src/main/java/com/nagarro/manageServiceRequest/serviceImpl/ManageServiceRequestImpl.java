package com.nagarro.manageServiceRequest.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;
import com.nagarro.manageServiceRequest.entity.ServiceRequest;
import com.nagarro.manageServiceRequest.service.CancelServiceRequest;
import com.nagarro.manageServiceRequest.service.ManageServiceRequest;
@Service
public class ManageServiceRequestImpl implements ManageServiceRequest {
	@Autowired 
	private JmsTemplate jmsTemplate;
	@Autowired
	PendingServiceRequest pendingServiceRequest;
	@Autowired
	ConfirmedServiceRequest confirmedServiceRequest;
	@Autowired
	InProcessServiceRequest inProcessServiceRequest;
	static List<ServiceRequest> serviceRequestList = new ArrayList<>();
	@Override
	public ServiceRequest findService(String serviceId) {
		Optional<ServiceRequest> resultServiceRequest = serviceRequestList.stream()
				.filter(serviceRequest -> serviceRequest.getId().equals(serviceId)).findFirst();
		if (resultServiceRequest.isPresent())
			return resultServiceRequest.get();
		else
			return null;
	}

	@Override
	public String createServiceRequest(ServiceRequest serviceRequest) {
		serviceRequestList.add(serviceRequest);
		System.out.println("Before JMS");
		jmsTemplate.convertAndSend("ServiceRequestReceivedEvent", serviceRequest);
		return serviceRequest.getId();
	}

	@Override
	public CancelServiceRequest getCancelRequestType(String serviceId) {
		ServiceRequest serviceRequest = findService(serviceId);
		ServiceRequestStatus status = serviceRequest.getStatusOfRequest();
		switch (status) {
		case PROCESSING:
			return pendingServiceRequest;
		case CONFIRMED:
			return confirmedServiceRequest;
		case PENDING:
			return inProcessServiceRequest;
		default:
			return null;

		}
	}

}
