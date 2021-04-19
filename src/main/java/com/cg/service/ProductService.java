package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.exception.InvalidModelNumberException;
import com.cg.repository.ComplaintRepositoryInterface;
import com.cg.repository.EngineerRepositoryInterface;
import com.cg.repository.ProductRepositoryInterface;

@Service
public class ProductService implements ProductServiceInterface {

	@Autowired
	ProductRepositoryInterface pr;
	@Autowired
	EngineerRepositoryInterface er;
	@Autowired
	ComplaintRepositoryInterface cmr;

	@Override
	public String addProductService(Product product) {
		LocalDate dt=product.getDateOfPurchase().plusYears(product.getWarrantyYear());
		product.setWarrantyDate(dt);
		pr.save(product);
		return "Product Added Successfully";
	}

	@Override
	public String deleteProductService(String category) {
		List<Product> p=pr.findAll().stream().filter(f->f.getProductCategoryName().equalsIgnoreCase(category)).collect(Collectors.toList());
		pr.deleteAll(p);
		return "Deleted All Products that belongs to '"+category+"'.";
	}

	@Override
	public List<Product> getProductService(String categoryName) {
		
		return pr.findAll().stream().filter(f->f.getProductCategoryName().equalsIgnoreCase(categoryName)).collect(Collectors.toList());
	}

	@Override
	public String updateProductWarrantyService(String modelNumber) throws InvalidModelNumberException {
		Optional<Product> p1=pr.findAll().stream().filter(f->f.getModelNumber().equalsIgnoreCase(modelNumber)).findFirst();
		if(p1.isEmpty()) {
			throw new InvalidModelNumberException("Invalid Model Number, Product Not Found");
		}
		Product p=p1.get();
		p.setWarrantyDate(p.getWarrantyDate().plusYears(1));
		p.setWarrantyYear(p.getWarrantyYear()+1);
		pr.save(p);
		return "Product Warranty Updated Successfully";
	}

	@Override
	public List<Complaint> getProductComplaintsService(String productCategoryName) {
		List<Complaint> c=cmr.findAll().stream().filter(f->f.getProduct().getProductCategoryName().equalsIgnoreCase(productCategoryName)).collect(Collectors.toList());
		return c;
	}

	@Override
	public List<Engineer> getEngineersByProductService(String productCategoryName) {
		List<Engineer> e=er.findAll().stream().filter(f->f.getDomain().equalsIgnoreCase(productCategoryName)).collect(Collectors.toList());
		return e;
	}


}
