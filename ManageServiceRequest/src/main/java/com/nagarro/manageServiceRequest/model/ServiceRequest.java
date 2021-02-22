package com.nagarro.manageServiceRequest.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ServiceRequest {
	String id;
	@NotEmpty(message = "Service Date is Mandatory")
	String date;
	@NotEmpty(message = "Service Receiver is Mandatory")
	String emailIdOfServiceReceiver;
	String emailIdOfServiceProvider;
	String specialRequirement;
	ServiceRequestStatus statusOfRequest;
}