package com.cg.entity;

public class Complaints {

	private int complaintId;
	private String complaintName;
	private String status;
	private int engineerId;
	private String clientId;
	private String modelNumber;
	
	public Complaints(int complaintId, String complaintName, String status, int engineerId, String clientId,
			String modelNumber) {
		super();
		this.complaintId = complaintId;
		this.complaintName = complaintName;
		this.status = status;
		this.engineerId = engineerId;
		this.clientId = clientId;
		this.modelNumber = modelNumber;
	}
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
	public int getEngineerId() {
		return engineerId;
	}
	public void setEngineerId(int engineerId) {
		this.engineerId = engineerId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}
