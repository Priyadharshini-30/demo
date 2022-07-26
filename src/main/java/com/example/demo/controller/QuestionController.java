package com.example.demo.controller;

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
import com.example.demo.dto.QuestionDto;
import com.example.demo.model.exam.Question;
import com.example.demo.model.exam.Quiz;

import com.example.demo.service.QuestionService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody QuestionDto questionDto)
	{
		Question questionRequest = modelMapper.map(questionDto, Question.class);
		if(questionRequest.getContent()==null || questionRequest.getContent().length()==0)
		{
			throw new EmptyInputException();
		}
		return ResponseEntity.ok(this.questionService.addQuestion(questionRequest));
	}
	
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody QuestionDto questionDto)
	{
		Question questionRequest = modelMapper.map(questionDto, Question.class);
		return ResponseEntity.ok(this.questionService.updateQuestion(questionRequest));
	}
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<Set<Question>> getQuestionsOfQuiz(@PathVariable("qid") Long qid)
	{
		Quiz quiz=new Quiz();
		quiz.setQid(qid);
		Set<Question> questions= this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questions);
	}
	
	@GetMapping("/{quesId}")
	public Optional<Question> getQuestions(@PathVariable("quesId") Long quesId)
	{
		return this.questionService.getQuestion(quesId);
	}
	
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId)
	{
		
		this.questionService.deleteQuestion(quesId);
	}
}
