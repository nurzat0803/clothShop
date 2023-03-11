package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.Order;
import com.example.clothshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    // get employee by id rest api
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order not exist with id :" + id));
        return ResponseEntity.ok(order);
    }

    // update employee rest api

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id,
                                                           @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order not exist with id :" + id));

        order.setUser_id(orderDetails.getUser_id());
        order.setStatus_id(orderDetails.getStatus_id());
        order.setDate_start(orderDetails.getDate_start());
        order.setDate_change_last_start(orderDetails.getDate_change_last_start());
        order.setOrderDetails(orderDetails.getOrderDetails());

        Order updateOrder = orderRepository.save(order);
        return ResponseEntity.ok(updateOrder);
    }

    // delete employee rest api
    @DeleteMapping("/order/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Integer id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("order not exist with id :" + id));

        orderRepository.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
