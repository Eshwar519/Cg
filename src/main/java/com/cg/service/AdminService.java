package com.cg.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Admin;
import com.cg.entity.Complaint;
import com.cg.entity.Complaints;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.InvalidDomainException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.repository.AdminRepositoryInterface;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.repository.ProductRepositoryInterface;
import com.cg.repository.ProductRepositoryInterface;

@Service
public class AdminService implements AdminServiceInterface {

	boolean signin=false;
	@Autowired
	public AdminRepositoryInterface ar;
	
	@Autowired
	public EngineerRepositoryInterface er;
	
	@Autowired
	public ComplaintRepositoryInterface cr;
	
	@Autowired
	public ProductRepositoryInterface pr;
	
	@Override
	public Admin viewAdmin(Admin a) {
		Optional<Admin> aa = ar.findById(a.getAdminId());
		Admin aaa = aa.get();
		return aaa;
	}

	@Override
	public String createAdmin(Admin a) {
		ar.save(a);
		return "Admin created";
	}

	@Override
	public boolean addEngineer(Engineer e) {
		er.save(e);
		return true;
	}

	@Override
	public void changeDomain(int engineerId, String newDomain)throws InvalidDomainException, InvalidEngineerIdException {
		Optional<Engineer> e = er.findById(engineerId);
		if(e.isEmpty()) {
			throw new InvalidEngineerIdException("Invalid Engineer ID, Engineer Not Found");
		}
		List<String> domains=Arrays.asList("AC","Fridge","Cooler","Laptop","Fan");
		if(!domains.contains(newDomain)) {
			throw new InvalidDomainException("Invalid Domain, Please Give Correct Domain");
		}
		e.get().setDomain(newDomain);
		er.save(e.get());
	}

	@Override
	public void removeEngineer(int engineerId) throws InvalidEngineerIdException {
		Optional<Engineer> e=er.findById(engineerId);
		if(e.isEmpty()) {
			throw new InvalidEngineerIdException("Invalid Engineer ID, Engineer Not Found");
		}
		er.delete(e.get());
	}

	@Override
	public List<Complaint> getComplaintsByProducts(String productCategoryName) {
		List<Complaint> complaints = cr.findAll().stream().filter(f->f.getProduct().getProductCategoryName().equals(productCategoryName)).collect(Collectors.toList());
		
		return complaints;
	}

	@Override
	public List<Complaint> getComplaints(String status, String productCategoryName) {
		List<Complaint> complaints = cr.findAll().stream().filter(f->f.getProduct().getProductCategoryName().equals(productCategoryName) && f.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		return complaints;
	}

	@Override
	public Complaint replaceEngineerFromComplaint(int complaintId) throws InvalidComplaintIdException {
		Optional<Complaint> cc = cr.findById(complaintId);
		if(cc.isEmpty()) {
			throw new InvalidComplaintIdException("Invalid Complaint ID, Complaint Not Found");
		}
		Complaint c=cc.get();
		Engineer ee = er.findAll().stream().filter(f->f.getDomain().equalsIgnoreCase(c.getEngineer().getDomain()) && f.getEmployeeId()!=c.getEngineer().getEmployeeId()).findFirst().get();
		c.setEngineer(ee);
		cr.save(c);
		return c;
	}
	
	@Override
	public Admin adminSignIn(Admin a)throws InvalidCredentialsException{
		Optional<Admin> aa = ar.findById(a.getAdminId());
		if(aa.isEmpty()) {
			throw new InvalidCredentialsException("Incorrect UserId or Password. Please try again.");
		}
		signin=true;
		System.out.println("Welcome Admin "+aa.get().getAdminId()+"! Sign In Successful");
		return aa.get();
	}

	@Override
	public Admin adminSignOut(Admin a)throws InvalidCredentialsException{
		Optional<Admin> aa = ar.findById(a.getAdminId());
		if(!signin) {
			throw new InvalidCredentialsException("Please SignIn");
		}
		signin=false;
		System.out.println("Thank You Admin"+a.getAdminId()+"  You have successfully logged out");
		return aa.get();
	}
	

}
