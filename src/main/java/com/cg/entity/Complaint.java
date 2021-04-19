package com.cg.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Complaint {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int complaintId;
	
	@NotNull(message = "Please describe your complaint")
	private String complaintName;
	
	@NotNull(message = "Please write 'Open' in status")
	private String status;
	@OneToOne
	@JoinColumn(name="engineer_id")
	private Engineer engineer;
	@OneToOne
	@JoinColumn(name="client_id")
	private Client client;
	@OneToOne
	@JoinColumn(name="product_model_number")
	private Product product;
	
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getComplaintName() {
		return complaintName;
	}
	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product=product;
	}
	public Engineer getEngineer() {
		return engineer;
	}
	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + complaintId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		if (complaintId != other.complaintId)
			return false;
		return true;
	}
}
