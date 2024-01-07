package com.vinicius.dscommerce.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.vinicius.dscommerce.entities.User;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private LocalDate birthDate;
	private List<String> roles = new ArrayList<>();
	
	public UserDTO() {
	}

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		phone = user.getPhone();
		birthDate = user.getBirthDate();
		for(GrantedAuthority x : user.getRoles()) {
			roles.add(x.getAuthority());
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public List<String> getRoles() {
		return roles;
	}
}
