package com.cg.service;

import java.util.List;

import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.InvalidDomainException;
import com.cg.exception.InvalidEngineerIdException;

public interface AdminServiceInterface {

	Admin viewAdmin(Admin a);
	String createAdmin(Admin a);

	public boolean addEngineer(Engineer e);
	public void changeDomain(int engineerId,String newDomain)throws InvalidDomainException,InvalidEngineerIdException;
	public void removeEngineer(int engineerId)throws InvalidEngineerIdException;
	
	public List<Complaint> getComplaintsByProducts(String productCategoryName);
	public List<Complaint> getComplaints(String status,String productCategoryName);
	
	public Complaint replaceEngineerFromComplaint(int complaintId)throws InvalidComplaintIdException; // replace engineer from the complaint and allocate new engineer
	Admin adminSignIn(Admin a) throws InvalidCredentialsException;
	Admin adminSignOut(Admin a) throws InvalidCredentialsException;
}
