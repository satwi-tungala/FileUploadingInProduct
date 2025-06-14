package com.example.githubproduct.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.githubproduct.model.ProductEntity;
import com.example.githubproduct.repository.ProductRepository;

@Service
public class ProductServicesImpl implements ProductServices{

	private static Logger log = LoggerFactory.getLogger(ProductServicesImpl.class);
	@Autowired
	private ProductRepository repository;
	@Override
	public ProductEntity saveProduct(String productName, String productDescription, MultipartFile productImage) throws IOException{
		try {
			ProductEntity product = new ProductEntity();
			product.setProductName(productName);
			product.setProductDescription(productDescription);
			product.setProductImage(productImage.getBytes());
			ProductEntity savedProduct = repository.save(product);
			log.info("Product saved successfully with ID: {} "+ savedProduct.getProductId());
			return savedProduct;
		}
		catch(Exception e){
			log.error("Error while saving Product: {}"+ e.getMessage());
			throw new RuntimeException("Error Saving Product");
		}
	}
		@Override
		public List<ProductEntity> getAllProducts(){
			try {
				 List<ProductEntity> products = repository.findAll();
				 log.info("Fetched Products {}", products.size());
				 return products;
			}
			catch(Exception e) {
				 log.error("Error fetching products: {}", e.getMessage());
		         throw new RuntimeException("Error fetching products");
			}
		}
		@Override
		public List<ProductEntity> getProductsByName(String productName){
			try {
				List<ProductEntity> products = repository.findByProductName(productName);
				log.info("Fetched Products with name: {}", products.size(), productName);
				return products;
			}
			catch(Exception e) {
				log.error("Error fetching products by name: {}", e.getMessage());
				throw new RuntimeException("Error fetching products by name");
		    }
		}
		@Override
		public ProductEntity updateProduct(Long productId, String productName, String productDescription, MultipartFile productImage ) {
			try {
	            Optional<ProductEntity> optionalProduct = repository.findById(productId);
	            if (optionalProduct.isPresent()) {
	                ProductEntity product = optionalProduct.get();
	                product.setProductName(productName);
	                product.setProductDescription(productDescription);
	                product.setProductImage(productImage.getBytes());
	                ProductEntity updatedProduct = repository.save(product);
	                log.info("Product updated successfully with ID: {}", productId);
	                return updatedProduct;
	            } else {
	                log.warn("Product not found with ID: {}", productId);
	                throw new RuntimeException("Product not found with ID: " + productId);
	            }
	        } catch (Exception e) {
	            log.error("Error updating product: {}", e.getMessage());
	            throw new RuntimeException("Error updating product");
	        }
			
		}
		 @Override
		    public String deleteProduct(Long id) {
		        try {
		            Optional<ProductEntity> optionalProduct = repository.findById(id);
		            if (optionalProduct.isPresent()) {
		                repository.deleteById(id);
		                log.info("Product deleted successfully with ID: {}", id);
		                return "Product deleted successfully with ID: " + id;
		            } else {
		                log.warn("Product not found with ID: {}", id);
		                return "Product not found with ID: " + id;
		            }
		        } catch (Exception e) {
		            log.error("Error deleting product: {}", e.getMessage());
		            throw new RuntimeException("Error deleting product");
		        }
		 }
		
}
