package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Complaints;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidDomainException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.service.EngineerServiceInterface;
import com.cg.service.AdminServiceInterface;


@RestController
@RequestMapping("Admin")
public class AdminController {
	@Autowired
	public AdminServiceInterface as;
	
	@Autowired
	public EngineerServiceInterface is;
	
	
	@GetMapping("viewAdmin/{id}")
	public Admin viewAdmin(@PathVariable("id") int id) {
		Admin a = new Admin();
		a.setAdminId(id);
		Admin aa = as.viewAdmin(a);
		return aa;
	}
	
	@PostMapping("/createAdmin")
	public String createAdmin(@RequestBody Admin a) {
		return as.createAdmin(a);
	}
	
	@PostMapping("/addEngineer")
	public boolean addEngineer(@RequestBody Engineer e) {
		as.addEngineer(e);
		return true;
	}
	
	
	@PutMapping("/changeDomain/{id}/{newDomain}")
	public void changeDomain(@PathVariable("id") int engineerId,@PathVariable("newDomain") String newDomain)throws InvalidDomainException, InvalidEngineerIdException{
		as.changeDomain(engineerId, newDomain);
	}
	
	@DeleteMapping("/removeEngineer/{id}")
	public void removeEngineer(int engineerId) throws InvalidEngineerIdException {
		as.removeEngineer(engineerId);
	}
	
	@GetMapping("/getComplaintsByProducsts/{productCategoryName}")
	public List<Complaints> getComplaintsByProducts(@PathVariable("productCategoryName") String productCategoryName){
		List<Complaint> list =  as.getComplaintsByProducts(productCategoryName);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: list) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		return cp1;
	}
	
	@GetMapping("/getComplaints/{status}/{productCategoryName}")
	public List<Complaints> getComplaints(@PathVariable("status") String status,@PathVariable("productCategoryName") String productCategoryName){
		List<Complaint> list =  as.getComplaintsByProducts(productCategoryName);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: list) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		return cp1;
	}
	
	@PutMapping("/replaceEngineerFromComplaint/{id}")
	public Complaints replaceEngineerFromComplaint(@PathVariable("id") int complaintId)throws InvalidDomainException, InvalidComplaintIdException{
		Complaint b = as.replaceEngineerFromComplaint(complaintId);
		Complaints cp1= new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber());
		return cp1;
	}
}
