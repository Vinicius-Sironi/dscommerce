package com.vinicius.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.dscommerce.DTOs.UserDTO;
import com.vinicius.dscommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping(value = "/me")
	public ResponseEntity<UserDTO> getMe() {
		UserDTO entity = service.getMe();
		return ResponseEntity.ok().body(entity);
	}
}
