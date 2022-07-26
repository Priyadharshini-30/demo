package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Quiz;



public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizzex();
	public Optional<Quiz> getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	public List<Quiz> getQuizzesOfCat(Category category) ;
}
