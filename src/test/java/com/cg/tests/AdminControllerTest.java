package com.cg.tests;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.service.AdminService;
import com.cg.service.ComplaintService;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

	@InjectMocks
	AdminService as;
	
	@Mock
	EngineerRepositoryInterface er;
	
	@Mock
	ComplaintRepositoryInterface cr;
	
	
	
	//complaint
	@InjectMocks
	ComplaintService cs;
	

	@Test
	public void addEngineerTest() {
		Engineer e = new Engineer();
		e.setEmployeeId(1); e.setEngineerName("ziya"); e.setPassword("pass"); e.setDomain("AC");
		when(er.save(e)).thenReturn(e);
		assertEquals(true, as.addEngineer(e));
	}
	
	@Test
	public void getComplaintsByProductsTest() {
		//Complaint Object 1
		
				Client client1=new Client();
				client1.setClientId("1");
				Product product1=new Product();
				product1.setModelNumber("1238");
				product1.setProductCategoryName("AC");
				Complaint complaint1=new Complaint();
				complaint1.setComplaintName("Ac-prob");
				complaint1.setStatus("Open");
				complaint1.setClient(client1);
				complaint1.setProduct(product1);
				
				
				//Complaint Object 2
				Client client2=new Client();
				client2.setClientId("2");
				Product product2=new Product();
				product2.setModelNumber("1237");
				product2.setProductCategoryName("AC");
				Complaint complaint2=new Complaint();
				complaint2.setComplaintName("Ac1-prob");
				complaint2.setStatus("Open");
				complaint2.setClient(client2);
				complaint2.setProduct(product2);
				
				//Complaint Object 3
				Client client3=new Client();
				client3.setClientId("3");
				Product product3=new Product();
				product3.setModelNumber("1239");
				product3.setProductCategoryName("Mobile");
				Complaint complaint3=new Complaint();
				complaint3.setComplaintName("Heating issue");
				complaint3.setStatus("Open");
				complaint3.setClient(client3);
				complaint3.setProduct(product3);
				
				
				List<Complaint> complaintList=new ArrayList<Complaint>();
				complaintList.add(complaint1);
				complaintList.add(complaint2);
				complaintList.add(complaint3);
				
				when(cr.findAll()).thenReturn(complaintList);
				assertEquals(1, as.getComplaintsByProducts("Mobile").size());
				assertEquals(2, as.getComplaintsByProducts("AC").size());
				
	}
	
	@Test
	public void removeEngineerTest() throws Exception{//delete not working
		Engineer e = new Engineer();
		e.setEmployeeId(1);
		e.setDomain("AC");
		Engineer e2 = new Engineer();
		e.setDomain("AC");
		e.setEmployeeId(2);
		er.save(e);
		er.save(e2);
		
		//when(er.findById(e.getEmployeeId()).get()).thenReturn(e);//.thenReturn(null);
		
		//er.delete(e);
		
		//verify(as).removeEngineer(e.getEmployeeId());
		
		
		//verify(as).removeEngineer(e);
		//when(er.deleteById(e.getEmployeeId())).th
		//when(er.delete(e))
		
		//when(er.findAll()).thenReturn(er.findById(null))
	}
	

}