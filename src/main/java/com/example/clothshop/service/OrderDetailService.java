package com.example.clothshop.service;


import com.example.clothshop.model.Language;
import com.example.clothshop.model.OrderDetail;
import com.example.clothshop.repository.LanguageRepository;
import com.example.clothshop.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository OrderDetailRepository;

    public OrderDetailService (OrderDetailRepository OrderDetailRepository) {
        this.OrderDetailRepository = OrderDetailRepository;
    }

    public List<OrderDetail> getAllOrderDetail() {
        return OrderDetailRepository.findAll();
    }

    public void saveOrderDetail(OrderDetail orderDetail) {
        this.OrderDetailRepository.save(orderDetail);
    }

    public OrderDetail getOrderDetailById(int id) {
        Optional<OrderDetail> optionalProduct = OrderDetailRepository.findById(id);
        OrderDetail orderDetail = null;
        if (optionalProduct.isPresent()) {
            orderDetail = optionalProduct.get();
        } else {
            throw new RuntimeException("Product not found for id : " + id);
        }
        return orderDetail;
    }

    public void deleteOrderDetailById(int id) {
        this.OrderDetailRepository.deleteById(id);
    }

}
