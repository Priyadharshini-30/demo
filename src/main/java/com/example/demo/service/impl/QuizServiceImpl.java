package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;

import com.example.demo.repo.QuizRepository;
import com.example.demo.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	Logger logger= LoggerFactory.getLogger(QuizServiceImpl.class);
	
	@Autowired
	private QuizRepository quixRepository;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		
		
		logger.info("Adding quiz");
		return this.quixRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		logger.info("updating quiz");
		return this.quixRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzex() {
		
		logger.info("Displaying quiz from db");
		return new HashSet<>(this.quixRepository.findAll());
	}

	@Override
	public Optional<Quiz> getQuiz(Long quizId) {
		
		logger.info("Fetching quiz from db");
		Optional<Quiz> quiz= this.quixRepository.findById(quizId);
		if(quiz.isPresent())
		{
			return quiz;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	@Override
	public void deleteQuiz(Long quizId) {
		
		logger.info("Deleting quiz");
		this.quixRepository.deleteById(quizId);
		
	}

	@Override
	public List<Quiz> getQuizzesOfCat(Category category) {
		return this.quixRepository.findBycategory(category);
	}

}
