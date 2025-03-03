package com.example.spring_m2_project_v2.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) { this.categoryService = categoryService; }

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping(path = "{categoryId}")
  public Category getCategoryById(@PathVariable Long categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  @PostMapping
  public Category createCategory(@RequestBody Category category) {
    return categoryService.createCategory(category);
  }

  @PutMapping(path = "{categoryId}/add-product/{productId}")
  public Category addProduct(@PathVariable Long categoryId, @PathVariable Long productId) {
    return categoryService.addProduct(categoryId, productId);
  }

  @PutMapping(path = "{categoryId}/remove-product/{productId}")
  public Category removeProduct(@PathVariable Long categoryId, @PathVariable Long productId) {
    return categoryService.removeProduct(categoryId, productId);
  }

  @DeleteMapping(path = "{categoryId}")
  public void deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategory(categoryId);
  }
}
