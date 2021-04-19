package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.entity.Engineer;

@Repository
public interface EngineerRepositoryInterface extends JpaRepository<Engineer, Integer> {

}
