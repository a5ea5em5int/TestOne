package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;

@Service
public interface ProductService  {
	
	void addProduct(Product product);
	List<Product> showAllProducts();
	void updateProduct(Product product);
	Product findProductById(int id);
	
	
	

}
