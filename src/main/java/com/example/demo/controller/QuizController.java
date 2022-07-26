package com.example.demo.controller;



import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
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
import com.example.demo.dto.QuizDto;
import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;
import com.example.demo.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController  {
	

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private QuizService quizService;
	

	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody QuizDto quizDto)
	{
		Quiz quizRequest = modelMapper.map(quizDto, Quiz.class);
		if(quizRequest.getTitle()==null || quizRequest.getTitle().length()==0)
		{
			throw new EmptyInputException();
		}
		return ResponseEntity.ok(this.quizService.addQuiz(quizRequest));
	}
	
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody QuizDto quizDto)
	{
		Quiz quizRequest = modelMapper.map(quizDto, Quiz.class);
		return ResponseEntity.ok(this.quizService.updateQuiz(quizRequest));
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> quizzex()
	{
		
		return ResponseEntity.ok(this.quizService.getQuizzex());
	}
	
	@GetMapping("/{qid}")
	public Optional<Quiz> quiz(@PathVariable("qid") Long qid)
	{
		
		return this.quizService.getQuiz(qid);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid)
	{
		Category category =new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesOfCat(category);
	}
	

	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid)
	{
		
		this.quizService.deleteQuiz(qid);
	}
	
	
	
}
