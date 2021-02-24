package com.nagarro.manageServiceRequest.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ServiceProvider{
	String id;
	String firstName;
	String lastName;
	String title;
	String email;
	String telePhone;
	String address;
	String district;
	String city;
	String state;
	Integer zip;
	String country;
	String password;
	String categoryId;
	List<String> notificationId;
	List<String> serviceRequestId;
	@Override
	public String toString() {
		return "ServiceProvider [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", email=" + email + ", telePhone=" + telePhone + ", address=" + address + ", district=" + district
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + ", password="
				+ password + ", categoryId=" + categoryId + ", notificationId=" + notificationId + ", serviceRequestId="
				+ serviceRequestId + "]";
	}
	
}
