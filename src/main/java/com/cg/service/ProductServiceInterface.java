package com.cg.service;

import java.util.List;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidModelNumberException;

public interface ProductServiceInterface {
	
	String addProductService(Product product);

	String deleteProductService(String category);

	List<Product> getProductService(String categoryName);

	String updateProductWarrantyService(String modelNumber) throws InvalidModelNumberException;

	List<Complaint> getProductComplaintsService(String productCategoryName);

	List<Engineer> getEngineersByProductService(String productCategoryName);
	
}
