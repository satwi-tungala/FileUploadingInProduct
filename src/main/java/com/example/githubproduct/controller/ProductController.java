package com.example.githubproduct.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.githubproduct.model.ProductEntity;
import com.example.githubproduct.service.ProductServices;
@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
	private ProductServices service;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(@RequestParam String productName,
    		                                    @RequestParam String productDescription,
                                                @RequestParam MultipartFile productImage) 
                                                throws IOException {
    ProductEntity savedProduct = service.saveProduct(productName, productDescription, productImage);
    return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully with ID: " + savedProduct.getProductId());
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
    	List<ProductEntity> products = service.getAllProducts();
    	return ResponseEntity.ok(products);
    }
    @GetMapping("/find/{productName}")
    public ResponseEntity<?> getProductsByName(@PathVariable String productName){
    	List<ProductEntity> products = service.getProductsByName(productName);
    	if(products.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No product found with name: " + productName);
    	}
    	   return ResponseEntity.ok(products);
    }
    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestParam String productName,
                                                @RequestParam String productDescription, @RequestParam MultipartFile productImage) 
                                                throws IOException {
        ProductEntity updatedProduct = service.updateProduct(productId, productName, productDescription, productImage);
        return ResponseEntity.ok("Product updated successfully with ID: " + updatedProduct.getProductId());
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = service.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
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
