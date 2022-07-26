package com.example.demo.controller;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.advice.EmptyInputException;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.exam.Category;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;


@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto)
	{
		Category categoryRequest = modelMapper.map(categoryDto, Category.class);
		
		if(categoryRequest.getTitile()==null || categoryRequest.getTitile().length()==0)
		{
			throw new EmptyInputException();
		}
		Category category=this.categoryService.addCategory(categoryRequest);
		return ResponseEntity.ok(category);
		
	}
	
	@GetMapping("/{categoryId}")
	public Optional<Category> getCategory(@PathVariable("categoryId") Long categoryId)
	{
		return this.categoryService.getCategory(categoryId);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getCategories()
	{
		logger.info("Displaying categories");
		logger.debug("In debug");
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	@PutMapping("/")
	public Category updateCategory(@RequestBody CategoryDto categoryDto)
	{
		Category categoryRequest = modelMapper.map(categoryDto, Category.class);
		return this.categoryService.updateCategory(categoryRequest);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
	}
	
}
