package com.cg.service;

import java.util.List;

import com.cg.entity.Client;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidCredentialsException;
import com.cg.exception.InvalidEngineerIdException;


public interface ClientServiceInterface {

	String saveClientService(Client c);

	Client getClientByClientIdService(String clientId) throws InvalidClientIdException;

	Engineer getEngineerByIdService(int id) throws InvalidEngineerIdException;

	List<Engineer> getEngineersByDomainService(String category);

	String changeStatusOfComplaintService(int complaintId);

	Client clientSignIn(Client c) throws InvalidCredentialsException;

	void clientSignOut() throws InvalidCredentialsException;

}
