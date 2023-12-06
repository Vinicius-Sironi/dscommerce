package com.vinicius.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.dscommerce.entities.Product;
import com.vinicius.dscommerce.entities.DTOs.ProductDTO;
import com.vinicius.dscommerce.repositories.ProductRepository;



@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageaple) {
		Page<Product> result = repository.findAll(pageaple);
		return result.map(x -> new ProductDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		return new ProductDTO(repository.findById(id).get());
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}
}
