package com.cg.service;

import java.util.List;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.exception.InvalidModelNumberException;
import com.cg.exception.OutOfWarrantyException;

public interface ComplaintServiceInteface {

	public Engineer getEngineer(int i) throws InvalidEngineerIdException;

	public boolean bookComplaintService(Complaint cm) throws InvalidClientIdException, InvalidModelNumberException, InvalidEngineerIdException, OutOfWarrantyException;

	public List<Complaint> getClientAllComplaintsService(Client e) throws InvalidClientIdException;

	public List<Complaint> getClientAllOpenComplaintsService(Client e) throws InvalidClientIdException;

	public String changeComplaintStatusService(Complaint cp) throws InvalidComplaintIdException;

	public Product getProductByComplaintService(int complaintId) throws InvalidComplaintIdException;
}
