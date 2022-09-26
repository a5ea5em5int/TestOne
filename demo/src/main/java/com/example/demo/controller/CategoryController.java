package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService cservice;
	
	@GetMapping("/categories")
	public String listCategories(Model model)
	{
		List<Category> clist = cservice.listCategories();
		model.addAttribute("categories", clist);
		return "categories";
	}
	
	@GetMapping("/categories/new")
	public String showInsertForm(Model model)
	
	{	Category category = new Category();
		model.addAttribute("category", category);
		return "category_form";		
	}
	@PostMapping("/categories/save")
	public String saveCategory(@ModelAttribute("category") Category category)
	{
		cservice.saveCategory(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit_form/{id}")
	public String show_category_editform(@PathVariable("id") Integer id,Model model)
	{	Category categoryOld = cservice.findById(id);
		model.addAttribute("category", categoryOld);
		return "category_edit_form";
		
	}
	@PostMapping("/categories/update")
	public String updateCategory(@ModelAttribute Category category)
	{	int id =category.getId(); 
		System.out.println("id val "+id);
		Category oCat = cservice.findById(id);
		oCat.setName(category.getName());
		cservice.saveCategory(oCat);
		return "redirect:/categories";
	}
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id )
	{
		cservice.deleteCategory(id);
		return "redirect:/categories";
	}
	
	@RequestMapping("/")
	  public void handleRequest() {
	      throw new RuntimeException("test exception");
	  }
}
