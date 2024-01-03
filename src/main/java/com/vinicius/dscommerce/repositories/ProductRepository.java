package com.vinicius.dscommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinicius.dscommerce.entities.Product;
import com.vinicius.dscommerce.projections.ProductProjection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(nativeQuery = true, value = 
			  "SELECT name, price, img_url AS imgUrl "
			+ "FROM tb_product "
			+ "WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) "
			+ "ORDER BY name ASC")
	Page<ProductProjection> searchByProductName(String name, Pageable pageable);
}
