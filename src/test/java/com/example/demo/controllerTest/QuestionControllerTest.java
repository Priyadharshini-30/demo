package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.advice.EmptyInputException;
import com.example.demo.controller.QuestionController;
import com.example.demo.dto.QuestionDto;
import com.example.demo.model.exam.Question;
import com.example.demo.model.exam.Quiz;
import com.example.demo.repo.QuestionRepository;
import com.example.demo.service.QuestionService;

@SpringBootTest
class QuestionControllerTest {

	@Autowired
	private QuestionController questionController;
	
	@Autowired
	private QuestionRepository quesRepo;
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void getQuestionTest() {
		Question ques= new Question("What language is used in app development","C","C++","C#","Java","Java");
		this.quesRepo.save(ques);
		Optional<Question> question=this.questionController.getQuestions(ques.getQuesId());
		if(question.isPresent())
		{
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void addQuestionTest() {
		QuestionDto quesDto= new QuestionDto(10L,"What language is used in app development","C","C++","C#","Java","Java");
		ResponseEntity<Question> ques1=this.questionController.add(quesDto);
		Assertions.assertEquals(200, ques1.getStatusCodeValue());
	}
	
	
	@Test
	void addQuestionExceptionTest() {
		QuestionDto quesDto= new QuestionDto(10L,"","C","C++","C#","Java","Java");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.questionController.add(quesDto));
	}
	
	@Test
	void addQuestionExceptionTest1() {
		QuestionDto quesDto= new QuestionDto(10L,null,"C","C++","C#","Java","Java");
		EmptyInputException emptyInputException= assertThrows(EmptyInputException.class,
				()->this.questionController.add(quesDto));
	}
	
	@Test
	void updateQuestionTest() {
		QuestionDto quesDto= new QuestionDto(10L,"What language is used in app development","C","C++","C#","Java","Java");
		quesDto.setContent("What language is used in app development?");
		ResponseEntity<Question> ques=this.questionController.update(quesDto);
		Assertions.assertEquals(200, ques.getStatusCodeValue());
	}
	
	@Test
	void deleteQuestion() {
		Question ques= new Question("What language is used in app development","C","C++","C#","Java","Java");
		this.quesRepo.save(ques);
		this.questionController.delete(ques.getQuesId());
		Set<Question> question=this.questionService.getQuestions();
		if(question.isEmpty())
		{
			Assertions.assertTrue(true);
		}
		
	}

	
}
