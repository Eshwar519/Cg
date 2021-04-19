package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.entity.Client;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidClientIdException;
import com.cg.exception.InvalidComplaintIdException;
import com.cg.exception.InvalidEngineerIdException;
import com.cg.exception.InvalidModelNumberException;
import com.cg.exception.OutOfWarrantyException;
import com.cg.repository.ClientRepositoryInterface;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.repository.ProductRepositoryInterface;
@Service
public class ComplaintService implements ComplaintServiceInteface {
	@Autowired
	ComplaintRepositoryInterface cd;
	@Autowired
	EngineerRepositoryInterface ed;
	@Autowired
	ProductRepositoryInterface pd;
	@Autowired
	ClientRepositoryInterface cr;
	@Override
	public Engineer getEngineer(int employeeId) throws InvalidEngineerIdException {
		Optional<Engineer> cc=ed.findById(employeeId);
		if(cc.isEmpty())
			throw new InvalidEngineerIdException("Invalid Engineer Id, Engineer Not Found on this Id");
		Engineer cc1=cc.get();
		return cc1;
	}
	@Override
	public boolean bookComplaintService(Complaint cp) throws InvalidClientIdException, InvalidModelNumberException, InvalidEngineerIdException, OutOfWarrantyException {

		Optional<Client> c=cr.findById(cp.getClient().getClientId());
		if(c.isEmpty()) {
			throw new InvalidClientIdException("Invalid Client Id, Client Not Found");
		}
		
		Optional<Product> p=pd.findById(cp.getProduct().getModelNumber());
		if(p.isEmpty()) { 
			throw new InvalidModelNumberException("Invalid Model Number, Product Not Found"); 
		}
		Product pp=p.get();
		Optional<Engineer>e=ed.findAll().stream().filter(f->f.getDomain().equals(pp.getProductCategoryName())).findAny();
		if(e.isEmpty()) {
			throw new InvalidEngineerIdException("Currently Engineer Not Available For this category");
		}
		if(pp.getWarrantyDate().compareTo(LocalDate.now())<0) {
			throw new OutOfWarrantyException("Product Warranty Expired");
		}
		  Engineer	e1=e.get(); 
		  cp.setEngineer(e1);

		cd.save(cp);
		return true;
	}
	@Override
	public List<Complaint> getClientAllComplaintsService(Client e) throws InvalidClientIdException {
		Optional<Client> c=cr.findById(e.getClientId());
		if(c.isEmpty()) {
			throw new InvalidClientIdException("Invalid Client Id, Client Not Found");
		}
		List<Complaint> cc=cd.findAll().stream().filter(p->p.getClient().getClientId().equals(e.getClientId())).collect(Collectors.toList());
		System.out.println(cc);
		return cc;
	}
	@Override
	public List<Complaint> getClientAllOpenComplaintsService(Client e) throws InvalidClientIdException {
		Optional<Client> c=cr.findById(e.getClientId());
		if(c.isEmpty()) {
			throw new InvalidClientIdException("Invalid Client Id, Client Not Found");
		}
		List<Complaint> c1=(List<Complaint>) cd.findAll().stream().filter(p->(p.getClient().getClientId().equals(e.getClientId())) && (p.getStatus().equals("Open"))).collect(Collectors.toList());
		
		return c1;
	}
	@Override
	public String changeComplaintStatusService(Complaint cp) throws InvalidComplaintIdException  {
		Optional<Complaint> cc1= cd.findById(cp.getComplaintId());
		if(cc1.isEmpty()) {
			throw new InvalidComplaintIdException("Invalid Complaint Id, Complaint Not Found");
		}
		Complaint cc2=cc1.get();
		cc2.setStatus("Closed");
		cd.save(cc2);
		return "Complaint Closed";
	}
	@Override
	public Product getProductByComplaintService(int complaintId) throws InvalidComplaintIdException {
		Optional<Complaint> ccc1= cd.findById(complaintId);
		if(ccc1.isEmpty()) {
			throw new InvalidComplaintIdException("Invalid Complaint Id, Complaint Not Found");
		}
		Complaint ccc2=ccc1.get();
		Optional<Product> p1=pd.findById(ccc2.getProduct().getModelNumber());
		Product p2=p1.get();
		return p2;
	}
	
}
