package com.example.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.CategoryController;
import com.example.demo.dto.CategoryDto;
import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Question;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;

@SpringBootTest

class CategoryControllerTest {
	
	@InjectMocks
	private CategoryController categoryController;
	
	@MockBean
	private CategoryService categoryService;

	private ModelMapper mapper;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@BeforeEach
	public void setup() {
	    this.mapper = new ModelMapper();
	}
	
	
	@Test
	void addCategoryTest() {
		CategoryDto categoryDto = new CategoryDto("C program","C questions");
		Category categoryRequest = this.mapper.map(categoryDto,Category.class);
		when(this.categoryService.addCategory(categoryRequest)).thenReturn(categoryRequest);
		ResponseEntity<Category> responseEntity = this.categoryController.addCategory(categoryDto);
		Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);	
	}
	
	@Test
	void getAllCategorytest(){
		
	}
	

}
