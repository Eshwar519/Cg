package com.cg.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.entity.Product;
import com.cg.repository.ProductRepositoryInterface;
import com.cg.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@InjectMocks
	ProductService ps;
	
	@Mock
	ProductRepositoryInterface pr;
	
	@Test
	public void addProductTest(){
		Product p = new Product();
		p.setModelNumber("123");
		p.setProductCategoryName("Laptop");
		p.setProductName("Macbook M1");
		p.setDateOfPurchase(LocalDate.now());
		pr.save(p);
		
		when(pr.save(p)).thenReturn(p);
		assertEquals("Product Added Successfully", ps.addProductService(p));
	}
	
	@Test
	public void getProductsTest() {
		Product p1 = new Product();
		p1.setModelNumber("123");
		p1.setProductCategoryName("Laptop");
		p1.setProductName("Macbook M1 13 inch");
		p1.setDateOfPurchase(LocalDate.now());
		
		Product p2 = new Product();
		p2.setModelNumber("1234");
		p2.setProductCategoryName("Laptop");
		p2.setProductName("Macbook Pro 16 inch");
		p2.setDateOfPurchase(LocalDate.now());
		
		List<Product> plist = new ArrayList<Product>();
		
		plist.add(p1);
		plist.add(p2);
		
		pr.save(p1);
		pr.save(p2);
		
		when(pr.findAll()).thenReturn(plist);
		assertEquals(2, ps.getProductService("Laptop").size());
		
	}
	
	@Test
	public void updateProductWarrantyTest() throws Exception{
		Product p = new Product();
		p.setModelNumber("1236");
		p.setProductCategoryName("Laptop");
		p.setProductName("Macbook M1");
		p.setDateOfPurchase(LocalDate.now());
		p.setWarrantyDate(LocalDate.now().plusYears(2));
		
		pr.save(p);
		when(pr.findAll()).thenReturn(Arrays.asList(p));
		assertEquals("Product Warranty Updated Successfully", ps.updateProductWarrantyService("1236"));
	}
	
	@Test
	void test() {
		//fail("Not yet implemented");
	}

}
