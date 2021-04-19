package com.cg.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Product {
	
	@Id
	@NotNull(message = "model number cannot be null")
	@Size(min=1,message = "please enter correct model number")
	private String modelNumber;
	
	@NotNull(message = "product name cannot be null")
	@Size(min=2,max=15,message = "please enter correct product name")
	private String productName;
	
	@NotNull(message = "product category cannot be null")
	@Size(min=2,max = 20, message = "please enter correct product category")
	private String productCategoryName;
	
	@NotNull(message = "warranty year cannot be null")
	@Min(1)
	private int warrantyYear;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate warrantyDate;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "please provide a date")
	private LocalDate dateOfPurchase;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public int getWarrantyYear() {
		return warrantyYear;
	}
	public void setWarrantyYear(int warrantyYear) {
		this.warrantyYear = warrantyYear;
	}
	public LocalDate getWarrantyDate() {
		return warrantyDate;
	}
	public void setWarrantyDate(LocalDate warrantyDate) {
		this.warrantyDate = warrantyDate;
	}
	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}


}
