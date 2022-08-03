package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.model.exam.Category;
import com.example.demo.model.exam.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuizDto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long qid;
	
	private String title;
	
	private String description;
	
	private String maxMarks;
	
	private String noOfQuestions;
	
	private boolean active=false;

	
	public QuizDto() {
		super();
	}

	public QuizDto(String title, String description, String maxMarks, String noOfQuestions) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.noOfQuestions = noOfQuestions;
	}
	

	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy="quiz",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions= new HashSet<>();

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(String noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
