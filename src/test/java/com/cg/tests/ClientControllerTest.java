package com.cg.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import com.cg.repository.ClientRepositoryInterface;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.service.ClientService;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

	@InjectMocks
	ClientService cs;
	
	@Mock
	ComplaintRepositoryInterface cpr;
	
	@Mock
	ClientRepositoryInterface cr;
	
	@Mock
	EngineerRepositoryInterface er;
	
	@Test
	public void saveClientTest() {
		Client c = new Client();
		c.setClientId("1");
		c.setPassword("pass");
		c.setPhoneNumber(998565855);
		c.setAddress("Hyd");
		
		when(cr.save(c)).thenReturn(c);
		assertEquals("Client Saved Successfully", cs.saveClientService(c));
		verify(cr).save(c);
	}
	
	@Test
	public void getEngineersByDomainTest() {
		Engineer e = new Engineer();
		e.setEmployeeId(1); e.setEngineerName("Raju"); e.setPassword("pass"); e.setDomain("AC");
		
		Engineer e2 = new Engineer();
		e2.setEmployeeId(2); e2.setEngineerName("Rahul"); e2.setPassword("pass"); e2.setDomain("AC");
		
		Engineer e3 = new Engineer();
		e3.setEmployeeId(3); e3.setEngineerName("Ziya"); e3.setPassword("pass"); e3.setDomain("Laptop");
		
		List<Engineer> elist = Arrays.asList(e,e2,e3);
		
		when(er.findAll()).thenReturn(elist);
		assertEquals(2, cs.getEngineersByDomainService("AC").size());
		verify(er).findAll();
	}
	
	@Test
	public void changeStatusOfComplaintTest() {
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
		
		when(cpr.findById(complaint1.getComplaintId())).thenReturn(Optional.of(complaint1));
		cs.changeStatusOfComplaintService(complaint1.getComplaintId());
		assertEquals("Closed", complaint1.getStatus());
		verify(cpr).findById(complaint1.getComplaintId());
	}
}