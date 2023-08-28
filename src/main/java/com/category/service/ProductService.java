package com.category.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.category.Entity.Product;
import com.category.repository.ProductRepository;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
	private Logger logger=LoggerFactory.getLogger(CategoryService.class);


    public Page<Product> getAllProducts(int pageNumber , int pageSize) {
    	logger.debug("In getAllProducts()");

    	PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    	
        return productRepository.findAll(pageRequest);
    }

    public Product getProductById(Long id) {
    	logger.debug("In getProductById()");

        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
    	logger.debug("In createProduct()");

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
    	logger.debug("In updateProduct()");

        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }

        updatedProduct.setId(id);
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
    	logger.debug("In deleteProduct()");

        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
