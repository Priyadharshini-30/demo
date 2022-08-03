package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.advice.UserAlreadyPresentException;
import com.example.demo.advice.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;

@SpringBootTest
class UserTest {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void getUserNameTest() {
		User user=new User("priya","priya");
		Assertions.assertEquals(null, user.getLastName());
		Assertions.assertEquals(null, user.getFirstName());
		Assertions.assertEquals(null, user.getProfile());
		Assertions.assertEquals(null, user.getPhone());
		Assertions.assertEquals(null,user.getEmail());
		Assertions.assertEquals(null, user.getUserType());
		when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user);
		Assertions.assertEquals(user, this.userServiceImpl.getUserName(user.getUsername()));
	}
	
	@Test
	void createUserTest() throws UserAlreadyPresentException {
		User user1=new User("priya1","priya1");
		when(this.userRepository.save(user1)).thenReturn(user1);
		User local1=this.userServiceImpl.createUser(user1);
		Assertions.assertEquals(local1, user1);
	}
	
	@Test
	void createUserPresentAlreadyTest() throws UserAlreadyPresentException {
		User user1=new User("priya1","priya1");
		when(this.userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
		UserAlreadyPresentException userAlreadyPresentException = assertThrows(UserAlreadyPresentException.class,
				() -> this.userServiceImpl.createUser(user1));
	}
	
	@Test
	void typeUserTest() throws UserNotFoundException {
		User user=new User("priya","priya");
		when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user);
		User local=this.userServiceImpl.typeUser(user);
		Assertions.assertEquals(local, user);	
	}
	
	@Test
	void typeUserTestException() throws UserNotFoundException {
		User user=new User("priya","priya");
		when(this.userRepository.findByUsername(user.getUsername())).thenReturn(null);
		UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,
				() -> this.userServiceImpl.typeUser(user));	
	}
	
	@Test
	void typeUserTestException1() throws UserNotFoundException {
		User user=new User("priya","priya");
		User user1=new User("priya","priya1");
		when(this.userRepository.findByUsername(user.getUsername())).thenReturn(user1);
		UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class,
				() -> this.userServiceImpl.typeUser(user));	
	}
	
	
	
	
	
}
