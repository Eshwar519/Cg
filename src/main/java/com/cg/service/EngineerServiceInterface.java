package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.exception.InvalidCredentialsException;

public interface EngineerServiceInterface {

	List<Complaint> getAllOpenComplaintsService(Engineer e);

	List<Complaint> getResolvedComplaintsService(Engineer e);

	List<Complaint> getResolvedComplaintsByDateService(Engineer e, LocalDate date);

	List<Complaint> getComplaintsService(Engineer e, String status);

	String changeComplaintStatusService(int complaintId);

	Engineer engineerSignIn(Engineer e) throws InvalidCredentialsException;

	Engineer engineerSignOut(Engineer e) throws InvalidCredentialsException;

}
