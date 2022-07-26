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

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;
import com.example.demo.repo.QuizRepository;
import com.example.demo.service.QuizService;

@SpringBootTest
class QuizTest {
	
	@Autowired
	private QuizService quizService;
	
	@MockBean
	private QuizRepository quizRepo;
	
	@Test
	void getAllQuizTest() {
		when(this.quizRepo.findAll()).thenReturn(Stream
		.of(new Quiz(1L,"C quiz","C questions","50","50",new Category("program","prgm qns"))
				,new Quiz(2L,"C++ quiz","C++ questions","50","50",new Category("program","prgm qns"))).collect(
						Collectors.toList()));
		Assertions.assertEquals(2, this.quizService.getQuizzex().size());
	}
	
	@Test
	void getQuizById() {
		Optional<Quiz> quiz=Optional.ofNullable(new Quiz(1L,"C quiz","C questions","50","50",new Category("program","prgm qns")));
		when(this.quizRepo.findById(1L)).thenReturn(quiz);
		Optional<Quiz> quiz1=this.quizService.getQuiz(1L);
		Assertions.assertEquals(quiz, quiz1);
	}
	
	@Test
	void saveQuizTest() {
		Quiz quiz=new Quiz(1L,"C quiz","C questions","50","50",new Category("program","prgm qns"));
		when(this.quizRepo.save(quiz)).thenReturn(quiz);
		Assertions.assertEquals(quiz, this.quizService.addQuiz(quiz));
	}
	
	@Test
	void deleteQuizTest() {
		Quiz quiz=new Quiz(1L,"C quiz","C questions","50","50",new Category("program","prgm qns"));
		this.quizService.deleteQuiz(1L);
		verify(quizRepo,times(1)).deleteById(1L);
	}
	
	@Test
	void UpdateQuizTest() {
		Quiz quiz=new Quiz(1L,"C quiz","C questions","50","50",new Category("program","prgm qns"));
		quiz.setQid(2L);
		when(this.quizRepo.save(quiz)).thenReturn(quiz);
		Assertions.assertEquals(quiz, this.quizService.updateQuiz(quiz));
		Assertions.assertEquals(2L, quiz.getQid());
		Assertions.assertNotEquals(1L, quiz.getQid());	
	}
	

}
