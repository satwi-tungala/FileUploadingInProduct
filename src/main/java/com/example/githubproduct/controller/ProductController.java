package com.example.githubproduct.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.githubproduct.model.ProductEntity;
import com.example.githubproduct.service.ProductServices;
/**
 * REST controller for handling product-related operations such as upload, fetch, update, delete, and download.
 */
@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
	private ProductServices service;
    /**
     * Uploads a new product along with its image.
     * 
     * @param product_name Name of the product
     * @param prod_description Description of the product
     * @param product_img Multipart file for product image
     * @return Success message with product ID
     * @throws IOException If an error occurs during file processing
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(@RequestParam String productName,
    		                                    @RequestParam String productDescription,
                                                @RequestParam MultipartFile productImage) 
                                                throws IOException {
    ProductEntity savedProduct = service.saveProduct(productName, productDescription, productImage);
    return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully with ID: " + savedProduct.getProductId());
    }
    /**
     * Retrieves all available products.
     * @return List of all products
     */
    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
    	List<ProductEntity> products = service.getAllProducts();
    	return ResponseEntity.ok(products);
    }
    /**
     * Retrieves products by product name.
     * @param product_name Name of the product to search
     * @return Product list or error message if not found
     */
    @GetMapping("/find/{productName}")
    public ResponseEntity<?> getProductsByName(@PathVariable String productName){
    	List<ProductEntity> products = service.getProductsByName(productName);
    	if(products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found with name: " + productName);
    	}
    	   return ResponseEntity.ok(products);
    }
    /**
     * Updates an existing product by product ID.
     * 
     * @param product_id ID of the product to update
     * @param product_name Updated product name
     * @param prod_description Updated product description
     * @param product_img Updated product image
     * @return Success or error message
     * @throws IOException If an error occurs during file processing
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestParam String productName,
                                                @RequestParam String productDescription, @RequestParam MultipartFile productImage) 
                                                throws IOException {
        ProductEntity updatedProduct = service.updateProduct(productId, productName, productDescription, productImage);
        return ResponseEntity.ok("Product updated successfully with ID: " + updatedProduct.getProductId());
    }
    /**
     * Deletes a product by ID.
     * 
     * @param product_id ID of the product to delete
     * @return Success or error message
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = service.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
    /**
     * Downloads the product image for the given product ID.
     * 
     * @param product_id ID of the product to download image
     * @return Product image file or error response
     */
    @GetMapping("/download/{productId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long productId) {
        List<ProductEntity> products = service.getAllProducts();
        for (ProductEntity product : products) {
            if (product.getProductId().equals(productId)) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + product.getProductName() + ".jpg\"")
                        .body(product.getProductImage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
