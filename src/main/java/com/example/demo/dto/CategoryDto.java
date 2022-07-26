package com.example.demo.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.model.exam.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cid;
	private String titile;
	private String description;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quizzes= new LinkedHashSet<>();
	
	
	
	public CategoryDto() {
		super();
	}

	public CategoryDto(String titile, String description) {
		super();
		this.titile = titile;
		this.description = description;
	}
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Quiz> getQuizzes() {
		return quizzes;
	}
	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
}
