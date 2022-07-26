package com.example.demo.service;



import com.example.demo.advice.UserAlreadyPresentException;
import com.example.demo.advice.UserNotFoundException;
import com.example.demo.model.User;


public interface UserService {

	
	
	public User createUser(User user) throws UserAlreadyPresentException;
	public User getUserName(String username);
	public User typeUser(User user) throws UserNotFoundException;
}
