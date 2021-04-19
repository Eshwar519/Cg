package com.cg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Complaint;
import com.cg.entity.Complaints;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidModelNumberException;
import com.cg.service.ProductServiceInterface;

@RestController
@RequestMapping("Product")
public class ProductController {
	@Autowired
	private ProductServiceInterface ps;
	
	@PutMapping("addProduct")
	public void addProduct(@Valid @RequestBody Product product) {
		String msg=ps.addProductService(product);
		System.out.println(msg);
	}
	
	
	@DeleteMapping("deleteProduct/{category}")
	public void removeProducts(@PathVariable("category") String category) {
		String msg=ps.deleteProductService(category);
		System.out.println(msg);
	}
	
	@GetMapping("getProduct/{category}")
	public List<Product>getProduct(@PathVariable("category") String categoryName){
		return ps.getProductService(categoryName);
	}
	
	@PutMapping("updateProductWarranty/{modelNumber}")
	public void updateProductWarranty(@PathVariable("modelNumber") String modelNumber)throws InvalidModelNumberException{
		String msg=ps.updateProductWarrantyService(modelNumber);
		System.out.println(msg);
	}
	
	@GetMapping("getProductComplaints/{category}")
	public List<Complaints> getProductComplaints(@PathVariable("category") String productCategoryName){
		List<Complaint>c= ps.getProductComplaintsService(productCategoryName);
		List<Complaints>cp1=new ArrayList<Complaints>();
		  for(Complaint b: c) {
			  cp1.add(new Complaints(b.getComplaintId(),b.getComplaintName(),b.getStatus(),b.getEngineer().getEmployeeId(),b.getClient().getClientId(),b.getProduct().getModelNumber()));
		  }
		  return cp1;
	}
	
	@GetMapping("getEngineersByProduct/{category}")
	public List<Engineer> getEngineersByProduct(@PathVariable("category") String productCategoryName){
		return ps.getEngineersByProductService(productCategoryName);
	}
}


