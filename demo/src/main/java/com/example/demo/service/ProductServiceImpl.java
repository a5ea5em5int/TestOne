package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository prepository;
	
	@Override
	public void addProduct(Product product) {
		prepository.save(product);
		
	}

	@Override
	public List<Product> showAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = prepository.findAll();
		return products;
	}

	@Override
	public void updateProduct(Product product) {
		   prepository.save(product);
		
	}

	@Override
	public Product findProductById(int id) {
		Product product = prepository.findById(id).get();
		return product;
	}

}
