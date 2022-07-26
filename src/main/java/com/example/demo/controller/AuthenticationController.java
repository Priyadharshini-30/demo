package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.advice.UserNotFoundException;
import com.example.demo.model.AuthRequest;
import com.example.demo.util.JwtUtil;

@CrossOrigin("*")
@RestController
public class AuthenticationController {
	
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    Logger logger=LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws UserNotFoundException{
        try {
        	
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
               
            } 
        catch (Exception ex) {
        	logger.info("user does not exists! invalid credentials");
            throw new UserNotFoundException();
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
