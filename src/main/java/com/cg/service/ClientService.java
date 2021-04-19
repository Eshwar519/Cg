package com.cg.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.repository.ClientRepositoryInterface;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;

@Service
public class ClientService implements ClientServiceInterface {
	static boolean signin;
	@Autowired
	ClientRepositoryInterface cr;
	@Autowired
	EngineerRepositoryInterface er;
	@Autowired
	ComplaintRepositoryInterface cmr;

	@Override
	public String saveClientService(Client c) {
		cr.save(c);
		return "Client Saved Successfully";
	}

	@Override
	public Client getClientByClientIdService(String clientId) throws InvalidClientIdException {
		Optional<Client> c=cr.findById(clientId);
		if(c.isEmpty()) {
			throw new InvalidClientIdException("Invalid ClientId, Not Client Found");
		}
		return c.get();
	}

	@Override
	public Engineer getEngineerByIdService(int id) throws InvalidEngineerIdException {
		Optional<Engineer> e=er.findById(id);
		if(e.isEmpty()) {
			throw new InvalidEngineerIdException("Invalid Engineer ID, Engineer Not Found");
		}
		return e.get();
	}

	@Override
	public List<Engineer> getEngineersByDomainService(String category) {
		
		return er.findAll().stream().filter(f->f.getDomain().equalsIgnoreCase(category)).collect(Collectors.toList());
	}

	@Override
	public String changeStatusOfComplaintService(int complaintId) {
		Complaint c=cmr.findById(complaintId).get();
		if(c.getStatus().equalsIgnoreCase("Open")) {
			c.setStatus("Closed");
		}
		cmr.save(c);
		return "Status Updated Successfully";
	}

	@Override
	public Client clientSignIn(Client c) throws InvalidCredentialsException {
		Optional<Client> cc = cr.findById(c.getClientId());
		if(cc.isEmpty()) {
			throw new InvalidCredentialsException("Incorrect UserId or Password. Please try again.");
		}
		signin=true;
		System.out.println("Welcome Client "+cc.get().getClientId()+"! Sign In Successful");
		return cc.get();
	}
	
	@Override
	public void clientSignOut() throws InvalidCredentialsException {
		if(!signin) {
			throw new InvalidCredentialsException("Please SignIn");
		}
		signin=false;
		System.out.println("Thank You Client You have successfully logged out");
		
	
}
}
