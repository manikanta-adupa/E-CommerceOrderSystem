package com.learning.ecommerceordersystem.service;

import com.learning.ecommerceordersystem.model.Order;
import com.learning.ecommerceordersystem.repository.OrderRepository;
import com.learning.ecommerceordersystem.repository.ProductRepository;
import com.learning.ecommerceordersystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

//    @Transactional
//    public Order createOrder(Order order) {
//
//    }
}
