package com.nagarro.manageServiceRequest.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;
import com.nagarro.manageServiceRequest.entity.ServiceRequest;
import com.nagarro.manageServiceRequest.model.AcceptServiceRequestResponse;
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
	@Autowired
	Gson gson;

	@Override
	public ServiceRequest findServiceRequest(String serviceRequestId) {
		Optional<ServiceRequest> resultServiceRequest = serviceRequestList.stream()
				.filter(serviceRequest -> serviceRequest.getId().equals(serviceRequestId)).findFirst();
		if (resultServiceRequest.isPresent())
			return resultServiceRequest.get();
		else
			return null;
	}

	@Override
	public String createServiceRequest(ServiceRequest serviceRequest) {
		serviceRequestList.add(serviceRequest);
		jmsTemplate.convertAndSend("ServiceRequestReceivedEvent", gson.toJson(serviceRequest));
		return serviceRequest.getId();
	}

	@Override
	public CancelServiceRequest getCancelRequestType(String serviceRequestId) {
		System.out.println("Inside Function ");
		System.out.println(findServiceRequest(serviceRequestId));
		ServiceRequest serviceRequest = findServiceRequest(serviceRequestId);
		if (serviceRequest != null) {
			ServiceRequestStatus status = serviceRequest.getStatusOfRequest();
			System.out.println("Status Of Request = " + status.toString());
			switch (status) {
			case PROCESSING:
				return this.inProcessServiceRequest;
			case CONFIRMED:
				return this.confirmedServiceRequest;
			case PENDING:
				return this.pendingServiceRequest;
			default:
				return null;

			}
		} else
			return null;
	}

	@JmsListener(destination = "ServiceRequestReceivedToAdmin")
	public void serviceRequestReceivedByAdmin(String serviceRequestId) {
		String serviceRequestIdObject = gson.fromJson(serviceRequestId, String.class);
		ServiceRequest serviceRequest = findServiceRequest(serviceRequestIdObject);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.PENDING);
	}

	@JmsListener(destination = "RequestAcceptedByServiceProviderEventForManageServiceRequest")
	public void requestAcceptedByServiceProvider(String acceptServiceRequestResponse) {
		System.out.println("Message = " + acceptServiceRequestResponse);
		AcceptServiceRequestResponse acceptServiceRequestResponseObject = gson.fromJson(acceptServiceRequestResponse,
				AcceptServiceRequestResponse.class);
		System.out.println(acceptServiceRequestResponseObject);
		ServiceRequest serviceRequest = findServiceRequest(acceptServiceRequestResponseObject.getServiceRequestId());
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CONFIRMED);
	}

	@Override
	@JmsListener(destination = "ServiceRequestCancelledByServiceProviderEventForManageServiceRequest")
	public void acceptCancelServiceRequestEventFromServiceProvider(String serviceRequestId) {
		String serviceRequestIdObject = gson.fromJson(serviceRequestId, String.class);
		ServiceRequest serviceRequest = findServiceRequest(serviceRequestIdObject);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CANCELLEDBYSERVICEPROVIDER);
	}

}
