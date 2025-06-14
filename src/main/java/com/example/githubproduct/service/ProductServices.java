package com.example.githubproduct.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.githubproduct.model.ProductEntity;

public interface ProductServices {

	ProductEntity saveProduct(String productName, String productDescription, MultipartFile productImage) throws IOException;
	List<ProductEntity> getAllProducts();
	List<ProductEntity> getProductsByName(String productName);
	ProductEntity updateProduct(Long productId, String productName, String productDescription, MultipartFile productImage);
	String deleteProduct(Long productId);
}
