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

import com.category.Entity.Category;
import com.category.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@GetMapping("/categories")
	public ResponseEntity<Page<Category>> getAllCategories(@RequestParam int page, @RequestParam(defaultValue = "5") int pageSize) {

		try {
			logger.debug("In getAllCategories()");
			
			 Page<Category> allCategories = categoryService.getAllCategories( page,pageSize);
			logger.debug("All Categories Are {}" + allCategories);
			return ResponseEntity.ok(allCategories);
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		try {
			logger.debug("In getCategoriesById()");
			Category result = this.categoryService.getCategoryById(id);
			logger.debug("perticulat categories are {}" + result);
			return ResponseEntity.of(Optional.of(result));
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		try {
			logger.debug("In createCategories()");
			Category result = this.categoryService.createCategory(category);
			logger.debug("Created Categorie is {}" + result);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);

		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
		try {
			logger.debug("In updatecategory()");
			Category result = this.categoryService.updateCategory(id, updatedCategory);
			logger.debug("Updated Category is" + result);
			return ResponseEntity.of(Optional.of(result));
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}

	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

		try {
			logger.debug("In deletecategory()");
			categoryService.deleteCategory(id);
			logger.debug("Catogery deleted");
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			logger.debug("" + e);
			return null;
		}
	}
}
