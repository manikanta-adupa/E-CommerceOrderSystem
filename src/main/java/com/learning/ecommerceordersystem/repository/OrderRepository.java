package com.learning.ecommerceordersystem.repository;

import com.learning.ecommerceordersystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
