package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.OrderDetail;
import com.example.clothshop.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/orderDetail")
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/orderDetail")
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    // get employee by id rest api
    @GetMapping("/orderDetail/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Integer id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("orderDetail not exist with id :" + id));
        return ResponseEntity.ok(orderDetail);
    }

    // update employee rest api

    @PutMapping("/orderDetail/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Integer id,
                                             @RequestBody OrderDetail orderDetailDetails) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("orderDetail not exist with id :" + id));

        orderDetail.setOrder_id(orderDetailDetails.getOrder_id());
        orderDetail.setGood_id(orderDetailDetails.getGood_id());
        orderDetail.setCost(orderDetailDetails.getCost());

        OrderDetail updateOrderDetail = orderDetailRepository.save(orderDetail);
        return ResponseEntity.ok(updateOrderDetail);
    }

    // delete employee rest api
    @DeleteMapping("/orderDetail/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderDetail(@PathVariable Integer id) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("orderDetail not exist with id :" + id));

        orderDetailRepository.delete(orderDetail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
