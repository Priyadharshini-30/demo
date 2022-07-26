package com.example.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.exam.Question;
import com.example.demo.repo.QuestionRepository;
import com.example.demo.service.QuestionService;

@SpringBootTest
class QuestionTest {

	@Autowired
	private QuestionService questionService;
	
	@MockBean
	private QuestionRepository questionRepo;
	
	@Test
	void getAllQuestionTest() {
		when(this.questionRepo.findAll()).thenReturn(Stream
		.of(new Question(10L,"What language is used in app development","C","C++","C#","Java","Java"),
				new Question(11L,"What language is used in app development","C","C++","C#","Kotlin","Kotlin")).collect(
						Collectors.toList()));
		Assertions.assertEquals(2, this.questionService.getQuestions().size());
	}
	
	@Test
	void getQuestionById() {
		Optional<Question> question=Optional.ofNullable(new Question(10L,"What language is used in app development","C","C++","C#","Java","Java"));
		when(this.questionRepo.findById(10L)).thenReturn(question);
		Optional<Question> question1=this.questionService.getQuestion(10L);
		Assertions.assertEquals(question, question1);
	}
	
	@Test
	void saveQuestionTest() {
		Question question= new Question(10L,"What language is used in app development","C","C++","C#","Java","Java");
		when(this.questionRepo.save(question)).thenReturn(question);
		Assertions.assertEquals(question, this.questionService.addQuestion(question));
	}
	
	@Test
	void deleteQuestionTest() {
		Question question= new Question(10L,"What language is used in app development","C","C++","C#","Java","Java");
		this.questionService.deleteQuestion(10L);
		verify(questionRepo,times(1)).deleteById(10L);
	}
	
	@Test
	void UpdateQuestionTest() {
		Question question= new Question(10L,"What language is used in app development","C","C++","C#","Java","Java");
		question.setQuesId(20L);
		when(this.questionRepo.save(question)).thenReturn(question);
		Assertions.assertEquals(question, this.questionService.updateQuestion(question));
		Assertions.assertEquals(20L, question.getQuesId());
		Assertions.assertNotEquals(10L, question.getQuesId());	
	}
	
}
