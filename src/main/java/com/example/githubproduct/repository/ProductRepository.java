package com.example.githubproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.githubproduct.model.ProductEntity;
/**
 * Repository interface for Product Entity. Provides database operations.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	 /**
     * Custom query to find products by name, case-insensitive.
     * @param product_name Name of the product
     * @return List of matching products
     */
	@Query(value = "SELECT * FROM productfile WHERE LOWER(product_name) = LOWER(:productName)", nativeQuery = true)
    List<ProductEntity> findByProductName(@Param("productName") String productName);
}
