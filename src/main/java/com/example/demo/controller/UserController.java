package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.advice.UserAlreadyPresentException;
import com.example.demo.advice.UserNotFoundException;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/signup")
	public User createUser(@RequestBody UserDto userDto) throws UserAlreadyPresentException{
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		userDto.setPassword(encoder.encode(userDto.getPassword()));
		
		User userRequest = modelMapper.map(userDto, User.class);

		return this.userService.createUser(userRequest);
		
		
	}
		
		
	
	@PostMapping("/type")
	public User typeUser(@RequestBody UserDto userDto) throws UserNotFoundException
	{
		User userRequest = modelMapper.map(userDto, User.class);
		return this.userService.typeUser(userRequest);
	}
	
	
}
