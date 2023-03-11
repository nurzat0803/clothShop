package com.example.clothshop.service;


import com.example.clothshop.model.Order;
import com.example.clothshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        this.orderRepository.save(order);
    }

    public Order getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order = null;
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        } else {
            throw new RuntimeException("Order not found for id : " + id);
        }
        return order;
    }

    public void deleteOrderById(int id) {
        this.orderRepository.deleteById(id);
    }
}