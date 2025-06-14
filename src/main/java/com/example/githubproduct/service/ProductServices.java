package com.example.githubproduct.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.githubproduct.model.ProductEntity;
/**
 * Service interface for managing product operations.
 */
public interface ProductServices {
	/**
     * Saves a new product.
     * @param product_name Product name
     * @param prod_description Product description
     * @param product_img Product image file
     * @return Saved product entity
     * @throws IOException If an error occurs while processing the image
     */
	ProductEntity saveProduct(String productName, String productDescription, MultipartFile productImage) throws IOException;
	 /**
     * Retrieves all products.
     * @return List of products
     */
	List<ProductEntity> getAllProducts();
	/**
     * Retrieves products by name.
     * @param product_name Name of the product
     * @return List of matching products
     */
	List<ProductEntity> getProductsByName(String productName);
	 /**
     * Updates an existing product.
     * @param product_id ID of the product to update
     * @param product_name New product name
     * @param prod_description New product description
     * @param product_img New product image
     * @return Updated product entity
     * @throws IOException If an error occurs while processing the image
     */
	ProductEntity updateProduct(Long productId, String productName, String productDescription, MultipartFile productImage);
	/**
     * Deletes a product by ID.
     * @param product_id Product ID
     * @return Success message
     */
	String deleteProduct(Long productId);
}
