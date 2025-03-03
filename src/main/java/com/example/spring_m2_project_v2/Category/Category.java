package com.example.spring_m2_project_v2.Category;

import com.example.spring_m2_project_v2.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Category {
  @Id
  @SequenceGenerator(
      name = "category_sequence",
      sequenceName = "category_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "category_sequence"
  )
  private Long id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
  @JsonIgnore
  private Set<Product> products = new HashSet<>();

  public Category() {
  }

  public Category(Long id, String name, String description, Set<Product> products) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.products = products;
  }

  public Category(String name, String description, Set<Product> products) {
    this.name = name;
    this.description = description;
    this.products = products;
  }

  public Category(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Category(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }

  public void addProduct(Product product) {
    this.products.add(product);
  }

  public void removeProduct(Product product) {
    this.products.remove(product);
  }

  @Override
  public String toString() {
    return "Category{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
