package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@Override
	public List<Category> listCategories() {
		// TODO Auto-generated method stub
		return crepository.findAll();
	}


	@Override
	public void saveCategory(Category category) {
		crepository.save(category);
		
	}


	@Override
	public Category findById(int id) {
		Category category = crepository.findById(id).get();
		return category;
	}


	@Override
	public void deleteCategory(int id) {
		crepository.deleteById(id);
		
		
	}

}
