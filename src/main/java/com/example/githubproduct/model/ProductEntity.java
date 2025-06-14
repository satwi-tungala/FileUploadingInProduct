package com.example.githubproduct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
/**
 * Entity class representing the product details
 * Stores product information including name, description, and image
 */
@Entity
@Table(name="productfile")
public class ProductEntity {
	/**
     * Unique identifier for the product of the Table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
    /**
     * Name of the product. Must be unique and not null.
     */
    @Column(nullable = false, unique = true)
	private String productName;
    /**
     * Description of the product.
     */
	private String productDescription;
	/**
     * Image file data of the product stored as byte array.
     */
	@Lob
	private byte[] productImage;
	 /**
     * Gets the product ID
     * @return Product ID
     */
	public Long getProductId() {
		return productId;
	}
	 /**
     * Sets the product ID
     * @param product_id Product ID to set
     */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
     * Gets the product Name
     * @return Product Name
     */
	public String getProductName() {
		return productName;
	}
	 /**
     * Sets the product Name
     */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
     * Gets the product Description
     * @return Product Description
     */
	public String getProductDescription() {
		return productDescription;
	}
	 /**
     * Sets the product Description
     */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
     * Gets the product image data.
     * @return Product image byte array
     */
	public byte[] getProductImage() {
		return productImage;
	}
	  /**
     * Sets the product image data.
     * @param product_img Product image as byte array
     */
	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	} 

}
