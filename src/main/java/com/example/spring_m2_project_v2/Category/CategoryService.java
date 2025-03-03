package com.example.spring_m2_project_v2.Category;

import com.example.spring_m2_project_v2.Product.Product;
import com.example.spring_m2_project_v2.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
    this.categoryRepository = categoryRepository;
    this.productRepository = productRepository;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Category getCategoryById(Long categoryId) {
    Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
    if (categoryOptional.isEmpty()) {
      throw new IllegalStateException("No category found with id: " + categoryId);
    }
    return categoryOptional.get();
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Category addProduct(Long categoryId, Long productId) {
    Category category = getCategoryById(categoryId);
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isEmpty()) {
      throw new IllegalStateException("No product found with id: " + productId);
    }
    Product product = productOptional.get();
    category.addProduct(product);
    Category oldCategory = product.getCategory();
    if (oldCategory != null) {
      oldCategory.removeProduct(product);
      categoryRepository.save(oldCategory);
    }
    product.setCategory(category);
    productRepository.save(product);
    return categoryRepository.save(category);
  }

  public Category removeProduct(Long categoryId, Long productId) {
    Category category = getCategoryById(categoryId);
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isEmpty()) {
      throw new IllegalStateException("No product found with id: " + productId);
    }
    Product product = productOptional.get();
    category.removeProduct(product);
    product.setCategory(null);
    productRepository.save(product);
    return categoryRepository.save(category);
  }

  public void deleteCategory(Long categoryId) {
    Category category = getCategoryById(categoryId);
    categoryRepository.delete(category);
  }
}
