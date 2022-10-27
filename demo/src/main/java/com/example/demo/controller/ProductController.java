package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyStore.Entry.Attribute;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	private CategoryService cservice;
	@Autowired
	private ProductService pservice;
	
	@GetMapping("/products/new")
	public String showProductForm(Model model)
	{
		Product product = new Product();
		model.addAttribute("product", product);
		List<Category> categories= cservice.listCategories();
		model.addAttribute("categories", categories);
		return "product_form";
	}

	@PostMapping("/products/save")
	public String saveProductForm(@ModelAttribute Product product,
			@RequestParam("image") MultipartFile file) throws IllegalStateException, IOException
	{  				String fileName = StringUtils.cleanPath(file.getOriginalFilename());  
	  				String uploadDir = "upload/"; // 1. creating upload dir
    	      		product.setFilename(uploadDir+fileName);// 2. concatenate filename and filepath and set to the product
					pservice.addProduct(product);//3. saving to table
					Path uploadPath = Paths.get(uploadDir); // 4.convert to path object
		if(!Files.exists(uploadPath))// checks whethere path exists
		{
			Files.createDirectories(uploadPath);
		}
		try (
			InputStream inputStream =file.getInputStream()){
			Path filePath = uploadPath.resolve(fileName); // concatenating filepath and filename usign path.resolve
			Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ioe) {throw new IOException("could not save image file"+fileName,ioe);}
		return "redirect:/products";
	}
	@GetMapping("/products")
	public String listProducts(Model model)
	{
		List<Product> products= pservice.showAllProducts();
		model.addAttribute("products", products);
		for (Product p: products)
		{
			System.out.println(p.getName());
		}
		return "products";
	}
	@GetMapping("/")
	public String showHome()
	{
		return "home";
	}
	

	@GetMapping("/products/edit_form/{id}")
	public String showProductForm(@PathVariable("id") Integer id, Model model)
	{	
		Product oprod= pservice.findProductById(id);
		 model.addAttribute("product", oprod);
		 List<Category> clist = cservice.listCategories();
			model.addAttribute("categories", clist);
		 return "product_edit_form";
	}
}
