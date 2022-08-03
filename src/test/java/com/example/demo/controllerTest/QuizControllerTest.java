package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.advice.EmptyInputException;
import com.example.demo.controller.QuizController;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Question;
import com.example.demo.model.exam.Quiz;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.QuizRepository;
import com.example.demo.service.QuizService;

@SpringBootTest
class QuizControllerTest {
	
	@Autowired
	private QuizController quizController;
	
	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private CategoryRepository categoryRepo; 
	
	@Test
	void getQuizTest() {
		Quiz quiz= new Quiz("C++","C questions","50","50");
		this.quizRepo.save(quiz);
		Optional<Quiz> quiz1=this.quizController.quiz(quiz.getQid());
		if(quiz1.isPresent())
		{
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void getAllQuizTest() {
		Quiz quiz= new Quiz("C++","C questions","50","50");
		this.quizRepo.save(quiz);
		ResponseEntity<Set<Quiz>> quiz1=this.quizController.quizzex();
		Assertions.assertEquals(200, quiz1.getStatusCodeValue());
	}
	
	@Test
	void addQuizTest() {
		QuizDto quizDto= new QuizDto("C#","C questions","50","50");
		ResponseEntity<Quiz> quiz=this.quizController.add(quizDto);
		Assertions.assertEquals(200, quiz.getStatusCodeValue());
	}
	
	@Test
	void addQuizExceptionTest() {
		QuizDto quizDto= new QuizDto("","C questions","50","50");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.quizController.add(quizDto));
	}
	
	@Test
	void addQuizExceptionTest1() {
		QuizDto quizDto= new QuizDto(null,"C questions","50","50");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.quizController.add(quizDto));
	}
	
	@Test
	void updateQuizTest() {
		QuizDto quizDto= new QuizDto("C++","C questions","50","50");
		quizDto.setTitle("C++ quiz new");
		ResponseEntity<Quiz> quiz=this.quizController.update(quizDto);
		Assertions.assertEquals(200, quiz.getStatusCodeValue());
	}
	
	@Test
	void deleteQuizTest() {
		Quiz quiz= new Quiz("C#","C questions","50","50");
		this.quizRepo.save(quiz);
		this.quizController.delete(quiz.getQid());
		Set<Quiz> quiz1=this.quizService.getQuizzex();
		if(quiz1.isEmpty())
		{
			Assertions.assertTrue(true);
		}
		
	}
	
	
	

}
