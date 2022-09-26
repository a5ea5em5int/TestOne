package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
@Service
public interface CategoryService {
	
	List<Category> listCategories();
	void saveCategory(Category category);
	Category findById(int id);
	void deleteCategory(int id);

}
