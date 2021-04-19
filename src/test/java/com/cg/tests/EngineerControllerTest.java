package com.cg.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.service.EngineerService;

@ExtendWith(MockitoExtension.class)
public class EngineerControllerTest {
	
	@InjectMocks
	EngineerService es;
	
	@Mock
	ComplaintRepositoryInterface cr;
	
	@Mock
	EngineerRepositoryInterface er;
	
	@Test
	public void getAllComplaintsTest() throws Exception{
		
				Engineer e=new Engineer();					//Engineer Object 1
				e.setEmployeeId(1);
				Complaint c=new Complaint();				//Complaint Object 1		
				c.setEngineer(e);
				Complaint c1=new Complaint();				//Complaint Object 2
				c1.setEngineer(e);
				List<Complaint> clist=new ArrayList<Complaint>();
				clist.add(c);
				clist.add(c1);
				when(cr.findAll()).thenReturn(clist);		
				assertEquals(2, es.getAllOpenComplaintsService(e).size());
	}
	
	@Test
	public void getAllResolvedComplaintsTest() throws Exception{
		
				Engineer e=new Engineer();					//Engineer Object 1
				e.setEmployeeId(1);
				Complaint c=new Complaint();				//Complaint Object 1
				c.setStatus("Resolved");					//Complaint Status Resolved
				c.setEngineer(e);
				Complaint c1=new Complaint();				//Complaint Object 2
				c1.setStatus("Open");					//Complaint Status Resolved
				c1.setEngineer(e);
				List<Complaint> clist=new ArrayList<Complaint>();
				clist.add(c);
				clist.add(c1);
				when(cr.findAll()).thenReturn(clist);		
				assertEquals(1, es.getResolvedComplaintsService(e).size());
	}
	
	@Test
	public void getResolvedComplaintsByDateTest() throws Exception{
		
				Engineer e=new Engineer();					//Engineer Object 1
				e.setEmployeeId(1);
				Complaint c=new Complaint();				//Complaint Object 1
				c.setStatus("Resolved");					//Complaint Status Resolved
				Product p=new Product();					//Product Object
				p.setDateOfPurchase(LocalDate.now());
				c.setProduct(p);
				c.setEngineer(e);
				//Complaint Object 2
				Complaint c1=new Complaint();				//Complaint Object 2
				c1.setStatus("Resolved");					//Complaint Status Resolved
				Product p1=new Product();					//Product Object 2
				p1.setDateOfPurchase(LocalDate.now());
				c1.setProduct(p1);
				c1.setEngineer(e);
				List<Complaint> clist=new ArrayList<Complaint>();
				clist.add(c);
				clist.add(c1);
				when(cr.findAll()).thenReturn(clist);		
				assertEquals(2, es.getResolvedComplaintsByDateService(e,LocalDate.now()).size());
	}
	
	@Test
	public void getComplaintsByStatusTest() throws Exception{
		
				Engineer e=new Engineer();					//Engineer Object 1
				e.setEmployeeId(1);
				Complaint c=new Complaint();				//Complaint Object 1
				c.setStatus("Open");					//Complaint Status Resolved
				c.setEngineer(e);
				//Complaint Object 2
				Complaint c1=new Complaint();				//Complaint Object 2
				c1.setStatus("Open");					//Complaint Status Resolved
				c1.setEngineer(e);
				List<Complaint> clist=new ArrayList<Complaint>();
				clist.add(c);
				clist.add(c1);
				when(cr.findAll()).thenReturn(clist);		
				assertEquals(2, es.getComplaintsService(e,"Open").size());
	}
	
	@Test
	public void changeComplaintStatusTest()throws Exception{
		Complaint c=new Complaint();
		c.setStatus("Open");
		c.setComplaintId(1);
		when(cr.findById(1)).thenReturn(Optional.of(c));
		assertEquals("Status Updated Successfully",es.changeComplaintStatusService(1));
	}
	
	@Test
	public void engineerSigninTest()throws Exception{
		Engineer e=new Engineer();
		e.setEmployeeId(1);
		e.setPassword("passs");
		when(er.findById(1)).thenReturn(Optional.of(e));
		assertEquals(e,es.engineerSignIn(e));
	}
	
}
