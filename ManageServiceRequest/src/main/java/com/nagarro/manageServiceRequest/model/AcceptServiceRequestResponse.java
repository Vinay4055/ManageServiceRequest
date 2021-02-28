package com.nagarro.manageServiceRequest.model;

import lombok.Getter;
import lombok.Setter;
import com.nagarro.manageServiceRequest.model.ServiceProvider;
@Getter
@Setter
public class AcceptServiceRequestResponse {
	public AcceptServiceRequestResponse() {
	}

	public AcceptServiceRequestResponse(ServiceProvider serviceProvider, String serviceRequestId) {
		super();
		this.serviceProvider = serviceProvider;
		this.serviceRequestId = serviceRequestId;
	}

	ServiceProvider serviceProvider;
	String serviceRequestId;

}
