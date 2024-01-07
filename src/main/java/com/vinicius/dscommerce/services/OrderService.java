package com.vinicius.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.dscommerce.DTOs.OrderDTO;
import com.vinicius.dscommerce.DTOs.OrderItemDTO;
import com.vinicius.dscommerce.entities.Order;
import com.vinicius.dscommerce.entities.OrderItem;
import com.vinicius.dscommerce.entities.Product;
import com.vinicius.dscommerce.entities.enums.OrderStatus;
import com.vinicius.dscommerce.repositories.OrderItemRepository;
import com.vinicius.dscommerce.repositories.OrderRepository;
import com.vinicius.dscommerce.repositories.ProductRepository;
import com.vinicius.dscommerce.services.exceptions.ResourceNotFoundException;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		return new OrderDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada")));
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		order.setClient(userService.authenticated());
		
		for(OrderItemDTO x : dto.getItems()) {
			Product product = productRepository.getReferenceById(x.getProductId());
			OrderItem orderItem = new OrderItem(order, product, x.getQuantity(), product.getPrice());
			order.getItems().add(orderItem);
		}
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}
}
