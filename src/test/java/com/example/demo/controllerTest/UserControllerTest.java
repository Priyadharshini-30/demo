package com.example.demo.controllerTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.advice.UserAlreadyPresentException;
import com.example.demo.advice.UserNotFoundException;
import com.example.demo.controller.UserController;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

@SpringBootTest
class UserControllerTest {
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	
	@Test
	void typeUserTest() throws UserNotFoundException {
		UserDto userDto=new UserDto("priya","priya");
		ModelMapper modelMapper=new ModelMapper();
		User user1=modelMapper.map(userDto, User.class);
		this.userRepo.save(user1);
		User user=this.userController.typeUser(userDto);
		Assertions.assertEquals(user.getUsername(), user1.getUsername());	
		Assertions.assertEquals(user.getPassword(), user1.getPassword());
	}
	
	@Test
	void craeteUserTest() throws UserAlreadyPresentException {
		UserDto userDto=new UserDto("priya","priya");
		ModelMapper modelMapper=new ModelMapper();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userDto.setPassword(encoder.encode(userDto.getPassword()));
		User user1=modelMapper.map(userDto, User.class);
		User user=this.userController.createUser(userDto);
		Assertions.assertEquals(user.getUsername(), user1.getUsername());	
		
	}
	
	@Test
	@AfterEach
	void deleteAll() {
		this.userRepo.deleteAll();
	}
	

}
