package com.vinicius.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.dscommerce.DTOs.OrderDTO;
import com.vinicius.dscommerce.repositories.OrderRepository;
import com.vinicius.dscommerce.services.exceptions.ResourceNotFoundException;



@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		return new OrderDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada")));
	}
}
