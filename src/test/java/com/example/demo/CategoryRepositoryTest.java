package com.example.demo;


import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.CategoryController;
import com.example.demo.model.exam.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceImpl;


@SpringBootTest
class CategoryRepositoryTest {
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	@Order(1)
	void saveCategoryTest() {
		Category category = new Category("C program","C questions");
		this.categoryService.addCategory(category);
		logger.info("A new category added");
		Assertions.assertThat(category).hasFieldOrPropertyWithValue("titile", "C program")
		.hasFieldOrPropertyWithValue("description", "C questions")
		.hasFieldOrPropertyWithValue("cid", category.getCid());
						
	}
	
	@Test
	@Order(5)
	void updateCategoryTest() {
		Category category = new Category("C program","C questions");
		category.setTitile("C new quiz");
		this.categoryService.updateCategory(category);
		Assertions.assertThat(category).hasFieldOrPropertyWithValue("titile", "C new quiz");
		
	}
	
	@Test
	@Order(2)
	void getAllCategoryTest() {
		Category category1 =new Category("C# program","C# questions");
		categoryRepo.save(category1);
		Category category2 =new Category("C1 program","C questions");
		categoryRepo.save(category2);
		Category category3 =new Category("C++ program","C++ questions");
		categoryRepo.save(category3);
		Iterable<Category> categories= this.categoryService.getCategories();
		Assertions.assertThat(categories).hasSize(3);
		logger.info("findAll() method works perfectly");
		Assertions.assertThat(category2).hasFieldOrPropertyWithValue("titile", "C1 program");
		Assertions.assertThat(category1).hasFieldOrPropertyWithValue("cid", category1.getCid());
		Assertions.assertThat(category3).hasFieldOrPropertyWithValue("cid", category3.getCid());	
	}
	
	
	@Test
	@Order(3)
	void getCategoryTest()
	{
		Category category4 =new Category("Java program","Java questions");
		categoryRepo.save(category4);
		Assertions.assertThat(category4).hasFieldOrPropertyWithValue("cid", category4.getCid());
		this.categoryService.getCategory(category4.getCid());
		logger.info("findById() works perfectly");	
	}
	
	@Test
	void getCategoryNotPresent() {
		Category category4 =new Category("Java program","Java questions");
		try {
			this.categoryService.getCategory(50L);
	        fail();
	    } catch (java.util.NoSuchElementException e) {}
	}
	

	@Test
	@Order(4)
	void deleteCategoryTest() 
	{
		Category category2 =new Category("C2 program","C questions");
		categoryRepo.save(category2);
		Category category3 =new Category("C3 program","C questions");
		categoryRepo.save(category3);
		this.categoryService.deleteCategory(category2.getCid());
		Iterable<Category> categories= this.categoryService.getCategories();
		Assertions.assertThat(categories).hasSize(1);
		logger.info("Successfully deleted");
	}
	
	@Test
	@AfterEach
	void deleteAllTest() {
		categoryRepo.deleteAll();	
		Iterable<Category> categories= this.categoryService.getCategories();
		Assertions.assertThat(categories).isEmpty();
	}
	
	

}
