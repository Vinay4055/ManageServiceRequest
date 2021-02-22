package com.nagarro.manageServiceRequest.model;

import javax.validation.constraints.NotEmpty;

import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ServiceRequest {
	@NotEmpty(message = "ServiceRequestId is Mandatory")
	String id;
	@NotEmpty(message = "ServiceId is Mandatory")
	String serviceId;
	@NotEmpty(message = "Date is Mandatory")
	String date;
	@NotEmpty(message = "Service Receiver is Mandatory")
	String emailIdOfServiceReceiver;
	String emailIdOfServiceProvider;
	String specialRequirement;
	ServiceRequestStatus statusOfRequest;
}