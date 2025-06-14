package com.example.githubproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.githubproduct.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	@Query(value = "SELECT * FROM productfile WHERE LOWER(product_name) = LOWER(:productName)", nativeQuery = true)
    List<ProductEntity> findByProductName(@Param("productName") String productName);
}
