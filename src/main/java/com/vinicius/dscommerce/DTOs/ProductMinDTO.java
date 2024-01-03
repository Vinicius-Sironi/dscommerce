package com.vinicius.dscommerce.DTOs;

import com.vinicius.dscommerce.projections.ProductProjection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductMinDTO {
	
	@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido!")
	private String name;
	
	@Positive(message = "O preço deve ser positivo")
	private Double price;
	
	private String imgUrl;
	
	public ProductMinDTO() {
	}
	
	public ProductMinDTO(ProductProjection projection) {
		name = projection.getName();
		price= projection.getPrice();
		imgUrl = projection.getImgUrl();
	}
	
	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
