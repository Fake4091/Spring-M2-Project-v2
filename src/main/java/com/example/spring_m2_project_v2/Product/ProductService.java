package com.example.spring_m2_project_v2.Product;

import com.example.spring_m2_project_v2.Category.Category;
import com.example.spring_m2_project_v2.Category.CategoryRepository;
import com.example.spring_m2_project_v2.Tag.Tag;
import com.example.spring_m2_project_v2.Tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final TagRepository tagRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public ProductService(ProductRepository productRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.tagRepository = tagRepository;
    this.categoryRepository = categoryRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long productId) {
    Optional<Product> productOptional = productRepository.findById(productId);
    if (productOptional.isEmpty()) {
      throw new IllegalStateException("No product found with id: " + productId);
    }
    return productOptional.get();
  }

  public Product createProduct(Product product) {
    if (product.getCategory() != null) {
      Category category = product.getCategory();
      if (product.getCategory().getId() == null || category.getId() == 0) {
        throw new IllegalStateException("Provided Category must be made before Product and have the correct id.");
      }
      Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
      if (categoryOptional.isEmpty()) {
        throw new IllegalStateException("No Category found with id: " + product.getCategory().getId());
      }
      category = categoryOptional.get();
      product.setCategory(category);
    }
    return productRepository.save(product);
  }

  public Product addTag(Long productId, Long tagId) {
    Product product = getProductById(productId);
    Optional<Tag> tagOptional = tagRepository.findById(tagId);
    if (tagOptional.isEmpty()) {
      throw new IllegalStateException("No tag found with id: " + tagId);
    }
    Tag tag = tagOptional.get();
    product.addTag(tag);
    tag.addProduct(product);
    tagRepository.save(tag);
    return productRepository.save(product);
  }

  public Product removeTag(Long productId, Long tagId) {
    Product product = getProductById(productId);
    Optional<Tag> tagOptional = tagRepository.findById(tagId);
    if (tagOptional.isEmpty()) {
      throw new IllegalStateException("No tag found with id: " + tagId);
    }
    Tag tag = tagOptional.get();
    product.removeTag(tag);
    tag.removeProduct(product);
    tagRepository.save(tag);
    return productRepository.save(product);
  }

  public void deleteProduct(Long productId) {
    Product product = getProductById(productId);
    productRepository.delete(product);
  }
}
