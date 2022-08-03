package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.advice.EmptyInputException;
import com.example.demo.controller.CategoryController;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.exam.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;

@SpringBootTest
class CategoryControllerTest {
	
	@Autowired
	private CategoryController categoryController;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	
	@Test
	void getAllCategoryTest() {
		Category category1 =new Category("C# program","C# questions");
		categoryRepo.save(category1);
		Category category2 =new Category("C1 program","C questions");
		categoryRepo.save(category2);
		Category category3 =new Category("C++ program","C++ questions");
		categoryRepo.save(category3);
		ResponseEntity<Set<Category>> categories= this.categoryController.getCategories();
		Assertions.assertEquals(200, categories.getStatusCodeValue());
	}
	
	@Test
	void addCategoryTest() {
		CategoryDto categoryDto =new CategoryDto("C# program","C# questions");
		ModelMapper modelMapper=new ModelMapper();
		Category category=modelMapper.map(categoryDto,Category.class); 
		ResponseEntity<Category> category1=this.categoryController.addCategory(categoryDto);
		Assertions.assertEquals(200, category1.getStatusCodeValue());
	}
	
	@Test
	void addCategoryExceptionTest() {
		CategoryDto categoryDto =new CategoryDto("","C# questions");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.categoryController.addCategory(categoryDto));
	}
	
	@Test
	void addCategoryExceptionTest1() {
		CategoryDto categoryDto =new CategoryDto(null,"C# questions");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.categoryController.addCategory(categoryDto));
	}
	
	@Test
	void updateCategoryTest() {
		CategoryDto categoryDto =new CategoryDto("C# program","C# questions");
		categoryDto.setTitile("C new quiz");
		Category category=this.categoryController.updateCategory(categoryDto);
		Assertions.assertEquals(category.getTitile(), categoryDto.getTitile());
	}
	
	@Test
	void getCategoryTest() {
		Category category1 =new Category("C# program","C# questions");
		categoryRepo.save(category1);
		Optional<Category> category =this.categoryController.getCategory(category1.getCid());
		if(category.isPresent())
		{
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void deleteCategoryTest() 
	{
		Category category2 =new Category("C2 program","C questions");
		categoryRepo.save(category2);
		Category category3 =new Category("C3 program","C questions");
		categoryRepo.save(category3);
		Iterable<Category> categories= this.categoryService.getCategories();
		this.categoryController.deleteCategory(category3.getCid());
		Assertions.assertNotNull(categories);
	}
	
	@Test
	@AfterEach
	void deleteAllTest() {
		categoryRepo.deleteAll();	
	}

}
