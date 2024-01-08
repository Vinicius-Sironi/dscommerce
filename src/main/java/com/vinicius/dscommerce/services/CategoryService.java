package com.vinicius.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.dscommerce.DTOs.CategoryDTO;
import com.vinicius.dscommerce.entities.Category;
import com.vinicius.dscommerce.repositories.CategoryRepository;



@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll(); 
		return list.stream().map(x -> new CategoryDTO(x)).toList();
	}
}
