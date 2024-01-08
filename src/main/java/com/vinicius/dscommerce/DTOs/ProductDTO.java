package com.vinicius.dscommerce.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.vinicius.dscommerce.entities.Category;
import com.vinicius.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;
	
	@Size(min = 10, message = "A descrição precisa ter pelo menos 10 caracteres")
	@NotBlank(message = "Campo requerido")
	private String description;
	
	@NotNull(message = "Campo requerido")
	@Positive(message = "O preço deve ser positivo")
	private Double price;
	
	private String imgUrl;
	
	
	@NotEmpty(message = "O produto deve ter pelo menos uma categoria")
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO() {
	}
	
	public ProductDTO(Product product) {
		id = product.getId();
		name = product.getName();
		description = product.getDescription();
		price = product.getPrice();
		imgUrl = product.getImgUrl();
		
		for(Category x : product.getCategoryes()) {
			categories.add(new CategoryDTO(x));
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}
}
