package com.example.spring_m2_project_v2.Tag;

import com.example.spring_m2_project_v2.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Tag {
  @Id
  @SequenceGenerator(
      name = "tag_sequence",
      sequenceName = "tag_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "tag_sequence"
  )
  private Long id;
  private String name;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "products",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private Set<Product> products = new HashSet<>();

  public Tag() {
  }

  public Tag(Long id, String name, Set<Product> products) {
    this.id = id;
    this.name = name;
    this.products = products;
  }

  public Tag(String name, Set<Product> products) {
    this.name = name;
    this.products = products;
  }

  public Tag(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Tag(String name) {
    this.name = name;
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
    return "Tag{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", products=" + products +
        '}';
  }
}
