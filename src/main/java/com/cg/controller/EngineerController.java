package com.cg.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Complaint;
import com.cg.entity.Complaints;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidCredentialsException;
import com.cg.service.EngineerServiceInterface;

@RestController
@RequestMapping("Engineer")
public class EngineerController {
	
	@Autowired
	EngineerServiceInterface es;
	
	@PostMapping("getAllOpenComplaints")
	public List<Complaints> getAllOpenComplaints(@RequestBody Engineer e){
		List<Complaint> c= es.getAllOpenComplaintsService(e);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	}
	
	@PostMapping("getResolvedComplaints")
	public List<Complaints> getResolvedComplaints(@RequestBody Engineer e){
		List<Complaint>c=es.getResolvedComplaintsService(e);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	}
	
	
	@PostMapping("getResolvedComplaintsByDate/{date}")
	public List<Complaints> getResolvedComplaintsByDate(@RequestBody Engineer e,@PathVariable ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		List<Complaint>c=es.getResolvedComplaintsByDateService(e,date);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	}
	
	@PostMapping("getComplaints/{status}")
	public List<Complaints> getComplaints(@RequestBody Engineer e,@PathVariable("status") String status){
		List<Complaint> c=es.getComplaintsService(e,status);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	}
	
	@PutMapping("changeComplaintStatus/{complaintId}")
	public String changeComplaintStatus(@PathVariable("complaintId") int complaintId) {
		String str=es.changeComplaintStatusService(complaintId);
		return str;
	}
	
	@PostMapping("engineerSignIn")
	public Engineer engineerSignIn(@RequestBody Engineer e) throws InvalidCredentialsException{
		Engineer ee = es.engineerSignIn(e);
		return ee;
	}
	
	@PostMapping("engineerSignOut")
	public Engineer engineerSignOut(@RequestBody Engineer e) throws InvalidCredentialsException{
		Engineer ee = es.engineerSignOut(e);
		return ee;
	}

}
