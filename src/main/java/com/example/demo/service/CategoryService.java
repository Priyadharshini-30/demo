package com.example.demo.service;

import java.util.Optional;
import java.util.Set;



import com.example.demo.model.exam.Category;



public interface CategoryService {

	public Category addCategory(Category category);
	public Category updateCategory(Category category);
	public Optional<Category> getCategory(Long categoryId);
	public Set<Category> getCategories();
	public void deleteCategory(Long categoryId);

}
