package com.vinicius.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vinicius.dscommerce.entities.Role;
import com.vinicius.dscommerce.entities.User;
import com.vinicius.dscommerce.projections.UserDetailsProjection;
import com.vinicius.dscommerce.repositories.UserRepository;

public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> list = repository.searchUserAndRolesByEmail(username);
		
		if(list.size() == 0) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = new User();
		user.setEmail(username);
		user.setPassword(list.get(0).getPassword());
		
		for(UserDetailsProjection x : list) {
			new Role(x.getRoleId(), x.getAuthority());
			user.addRole(new Role(x.getRoleId(), x.getAuthority()));
		}
		return user;
	}
	
	
}
