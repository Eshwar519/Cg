package com.cg.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.cg.entity.Client;

@Repository
public interface ClientRepositoryInterface extends JpaRepositoryImplementation<Client, String> {
	
}
