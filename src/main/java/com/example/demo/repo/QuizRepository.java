package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;


@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long>{

	public List<Quiz> findBycategory(Category category);

	
}
