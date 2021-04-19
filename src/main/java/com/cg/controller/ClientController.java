package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Client;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.service.ClientServiceInterface;

@RestController
@RequestMapping("Client")
public class ClientController {
	
	@Autowired
	ClientServiceInterface cs;
	
	@PutMapping("saveClient")
	public void saveClient(@Valid @RequestBody Client c) {
		String str=cs.saveClientService(c);
		System.out.println(str);
	}
	
	@GetMapping("getClientByClientId/{clientId}")
	public Client getClientByClientId(@PathVariable("clientId") String clientId)throws InvalidClientIdException{
		return cs.getClientByClientIdService(clientId);
	}
	
	@GetMapping("getEngineerById/{id}")
	public Engineer getEngineerById(@PathVariable("id") int id) throws InvalidEngineerIdException{
		return cs.getEngineerByIdService(id);
	}
	
	@GetMapping("getEngineersByDomain/{category}")
	public List<Engineer> getEngineersByDomain(@PathVariable("category") String category){
		return cs.getEngineersByDomainService(category);
	}
	
	@PutMapping("changeStatusOfComplaint/{complaintId}")
	public String changeStatusOfComplaint(@PathVariable("complaintId") int complaintId) {
		return cs.changeStatusOfComplaintService(complaintId);
	}
	@PostMapping("clientSignIn")
	public Client clientSignIn(@RequestBody Client c) throws InvalidCredentialsException{
		Client cc = cs.clientSignIn(c);
		return cc;
	}
	
	@PostMapping("clientSignOut")
	public void clientSignOut() throws InvalidCredentialsException{
		cs.clientSignOut();

	}


}

