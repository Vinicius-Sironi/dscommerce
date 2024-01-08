package com.vinicius.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.dscommerce.entities.User;
import com.vinicius.dscommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {
	
	@Autowired
	private UserService service;
	
	public void validateSelfOrAdmin(Long id) {
		User me = service.authenticated();
		
		if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(id)) {
			throw new ForbiddenException("Acess danied");
		}
	}
}
