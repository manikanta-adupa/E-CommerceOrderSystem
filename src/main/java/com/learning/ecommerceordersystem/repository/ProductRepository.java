package com.learning.ecommerceordersystem.repository;

import com.learning.ecommerceordersystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
