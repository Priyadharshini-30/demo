package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.advice.UserAlreadyPresentException;
import com.example.demo.advice.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User getUserName(String username) {
		return this.userRepository.findByUsername(username);
	}


	@Override
	public User typeUser(User user) throws UserNotFoundException {
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local==null)
		{
			logger.info("user does not exists in database");
			throw new UserNotFoundException();
		}
		else if(!(local.getPassword().equals(user.getPassword())))
		{
			logger.info("invalid credentials");
			throw new UserNotFoundException();
		}
		return local;
		
	}
	

	@Override
	public User createUser(User user) throws UserAlreadyPresentException {
		
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null )
		{
			logger.info("Cannot register! username already present");
			throw new UserAlreadyPresentException();
		}
		else
		{
			local=this.userRepository.save(user);
		}
		return local;
	}
	
	

}
