package com.nagarro.manageServiceRequest.model;

import javax.validation.constraints.NotEmpty;

import com.nagarro.manageServiceRequest.common.ServiceRequestStatus;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ServiceRequest {
	@NotEmpty(message="Service Request Id is mandatory")
	String id;//Coming in Model
	@NotEmpty(message = "ServiceId is Mandatory")
	String serviceId;
	@NotEmpty(message = "Date is Mandatory")
	String date;
	@NotEmpty(message = "Service Receiver is Mandatory")
	String emailIdOfServiceReceiver;
	String emailIdOfServiceProvider;
	String specialRequirement;
	ServiceRequestStatus statusOfRequest;
	@Override
	public String toString() {
		return "ServiceRequest [serviceId=" + serviceId + ", date=" + date + ", emailIdOfServiceReceiver="
				+ emailIdOfServiceReceiver + ", emailIdOfServiceProvider=" + emailIdOfServiceProvider
				+ ", specialRequirement=" + specialRequirement + ", statusOfRequest=" + statusOfRequest + "]";
	}
	
	
}