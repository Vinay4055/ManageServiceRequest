package com.nagarro.manageServiceRequest.service;

import org.springframework.stereotype.Service;

@Service
public interface CancelServiceRequest {
	public void cancelServiceRequest(String serviceId);
}
