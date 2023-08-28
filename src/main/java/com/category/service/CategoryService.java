package com.category.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.category.Entity.Category;
import com.category.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

	private Logger logger=LoggerFactory.getLogger(CategoryService.class);

    
    public Page<Category> getAllCategories(int page , int pageSize) {
    	logger.debug("In getAllCategories()");
    	PageRequest pageRequest = PageRequest.of(page, pageSize);
        return categoryRepository.findAll(pageRequest);
    }

    public Category getCategoryById(Long id) {
    	logger.debug("In getCategoryById()");
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
    	logger.debug("In createCategory()");
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
    	logger.debug("In  updateCategory()");
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        
        updatedCategory.setId(id);
        return categoryRepository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
    	logger.debug("In deleteCategory() ");

        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
