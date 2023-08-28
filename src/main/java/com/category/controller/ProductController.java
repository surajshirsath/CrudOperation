package com.category.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.category.Entity.Product;
import com.category.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
		try {
			logger.debug("In getAllProducts()");
			
			Page<Product> allProducts = productService.getAllProducts(page, pageSize);
			logger.debug("All Products are {}" + allProducts);
			return ResponseEntity.ok(allProducts);
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		try {
			logger.debug("In getProductById()");
			Product result = this.productService.getProductById(id);
			logger.debug("Product of given id are {}" + result);
			return ResponseEntity.of(Optional.of(result));
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			logger.debug("In createProduct()");
			Product result = this.productService.createProduct(product);
			logger.debug("This Product Created" + result);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

		try {
			logger.debug("In updateProduct()");
			Product result = this.productService.updateProduct(id, updatedProduct);
			logger.debug("Updated Product Are" + result);
			return ResponseEntity.of(Optional.of(result));
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		try {
			logger.debug("In deleteProduct()");
			productService.deleteProduct(id);
			logger.debug("Product Deleted");
			return ResponseEntity.status(HttpStatus.OK).build();

		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}
}
