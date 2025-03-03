package com.example.spring_m2_project_v2.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) { this.productService = productService; }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping(path = "{productId}")
  public Product getProductById(@PathVariable Long productId) {
    return productService.getProductById(productId);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping(path = "{productId}/add-tag/{tagId}")
  public Product addTag(@PathVariable Long productId, @PathVariable Long tagId) {
    return productService.addTag(productId, tagId);
  }

  @PutMapping(path = "{productId}/remove-tag/{tagId}")
  public Product removeTag(@PathVariable Long productId, @PathVariable Long tagId) {
    return productService.removeTag(productId, tagId);
  }

  @DeleteMapping(path = "{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }
}
