package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Complaints;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.exception.InvalidModelNumberException;
import com.cg.exception.OutOfWarrantyException;
import com.cg.service.ComplaintServiceInteface;

@RestController
@RequestMapping("Complaint")
public class ComplaintController {
	
	@Autowired
	ComplaintServiceInteface cs;

	
	  @GetMapping("getEngineer/{id}") 
	  public Engineer getEngineer(@PathVariable("id") int employeeId)throws InvalidEngineerIdException {
		  Engineer cc=cs.getEngineer(employeeId);
	  return cc;
			  }
	  
	  @PutMapping("bookComplaint")
	  public boolean bookComplaint(@Valid @RequestBody Complaint cp)throws InvalidEngineerIdException, OutOfWarrantyException, InvalidModelNumberException, InvalidClientIdException {
		  
		  boolean b=cs.bookComplaintService(cp);
		  return b;
	  }
	  @PostMapping("getClientAllComplaints")
	  public List<Complaints> getClientAllComplaints(@Valid @RequestBody Client e)throws InvalidClientIdException{
		  List<Complaint> c=cs.getClientAllComplaintsService(e);
		  List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	  }
	  @PostMapping("getClientAllOpenComplaints")
	  public List<Complaints> getClientAllOpenComplaints(@Valid @RequestBody Client e)throws InvalidClientIdException{
		  List<Complaint> c=cs.getClientAllOpenComplaintsService(e);
		  List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	  }
	  
	  @PutMapping("changeComplaintStatus")
	  public String changeComplaintStatus(@Valid @RequestBody Complaint cp)throws InvalidComplaintIdException {
		  String str=cs.changeComplaintStatusService(cp);
		  return str;
	  }
	  
	  @GetMapping("getProductByComplaint/{id}")
	  public Product getProductByComplaint(@PathVariable("id") int complaintId)throws InvalidComplaintIdException {
		  Product p=cs.getProductByComplaintService(complaintId);
		  return p;
	  }
	 
	
}
