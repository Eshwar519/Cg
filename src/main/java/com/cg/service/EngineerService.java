package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidCredentialsException;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;

@Service
public class EngineerService implements EngineerServiceInterface {
	
	static boolean signin;
	@Autowired
	EngineerRepositoryInterface er;
	@Autowired
	ComplaintRepositoryInterface cr;

	@Override
	public List<Complaint> getAllOpenComplaintsService(Engineer e) {
		
		return cr.findAll().stream().filter(f->f.getEngineer().getEmployeeId()==e.getEmployeeId()).collect(Collectors.toList());
	}

	@Override
	public List<Complaint> getResolvedComplaintsService(Engineer e) {
		
		return cr.findAll().stream().filter(f->f.getEngineer().getEmployeeId()==e.getEmployeeId()&& f.getStatus().equalsIgnoreCase("Resolved")).collect(Collectors.toList());
	}

	@Override
	public List<Complaint> getResolvedComplaintsByDateService(Engineer e, LocalDate date) {
		
		return cr.findAll().stream().filter(f->f.getEngineer().getEmployeeId()==e.getEmployeeId()&& f.getStatus().equalsIgnoreCase("Resolved") && f.getProduct().getDateOfPurchase().equals(date)).collect(Collectors.toList());
	}

	@Override
	public List<Complaint> getComplaintsService(Engineer e, String status) {
		return cr.findAll().stream().filter(f->f.getEngineer().getEmployeeId()==e.getEmployeeId()&& f.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
	}

	@Override
	public String changeComplaintStatusService(int complaintId) {
		Complaint c=cr.findById(complaintId).get();
		if(c.getStatus().equalsIgnoreCase("Open")) {
			c.setStatus("Resolve Online");
		}
		else if(c.getStatus().equalsIgnoreCase("Resolved Online")) {
			c.setStatus("Resolve After Home Visit");
		}
		else if(c.getStatus().equalsIgnoreCase("Resolve After Home Visit")) {
			c.setStatus("Resolved");
		}
		cr.save(c);
		return "Status Updated Successfully";
	}
	
	@Override
	public Engineer engineerSignIn(Engineer e) throws InvalidCredentialsException {
		
		Optional<Engineer> ee = er.findById(e.getEmployeeId());
		if(ee.isEmpty()||!(ee.get().getPassword().equals(e.getPassword()))) {
			throw new InvalidCredentialsException("Incorrect UserId or Password. Please try again.");
		}
		signin=true;
		System.out.println("Welcome Engineer "+e.getEmployeeId()+" Sign In Successful");
		return ee.get();
	}

	@Override
	public Engineer engineerSignOut(Engineer e) throws InvalidCredentialsException {
		if(!signin) {
			throw new InvalidCredentialsException("Please SignIn");
		}
		signin=false;
		System.out.println("Thank You Engineer "+e.getEmployeeId()+" You have successfully logged out");
		return e;
	}

}
