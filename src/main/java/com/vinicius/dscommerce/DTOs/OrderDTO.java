package com.vinicius.dscommerce.DTOs;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.vinicius.dscommerce.entities.Order;
import com.vinicius.dscommerce.entities.OrderItem;
import com.vinicius.dscommerce.entities.enums.OrderStatus;

import jakarta.validation.constraints.NotEmpty;

public class OrderDTO {
	
	private Long id;
	private Instant moment;
	private OrderStatus status;
	
	private ClientDTO client;
	
	private PaymentDTO payment;
	
	@NotEmpty(message = "Deve ter pelo menos um item")
	private List<OrderItemDTO> items = new ArrayList<>();
	
	public OrderDTO() {
	}
	
	public OrderDTO(Order entity) {
		id = entity.getId();
		moment = entity.getMoment();
		status = entity.getStatus();
		client = new ClientDTO(entity.getClient());
		payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
		
		for(OrderItem x : entity.getItems()) {
			items.add(new OrderItemDTO(x));
		}
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public ClientDTO getClient() {
		return client;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public double getTotal() {
		double sum = 0;
		
		for(OrderItemDTO x: items) {
			sum += x.getSubTotal();
		}
		return sum;
	}
}
