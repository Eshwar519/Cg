package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Client {
	@Id
	@NotNull(message = "client Id cannot be null")
	private String clientId;
	
	@NotNull(message = "password cannot be null")
	@Size(min=4,max=10, message = "Password must be greater than or equal to 5 characters and less than 10 characters")
	private String password;
	
	@NotNull(message = "address cannot be null")
	private String address;
	
	@NotNull(message = "PhoneNumber cannot be null")
	@Min(1000000000)
	private long phoneNumber;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

