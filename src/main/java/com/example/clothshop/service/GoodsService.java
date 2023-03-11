package com.example.clothshop.service;


import com.example.clothshop.model.Goods;
import com.example.clothshop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public List<Goods> getAllProduct() {
        return goodsRepository.findAll();
    }

    public void saveProduct(Goods goods) {
        this.goodsRepository.save(goods);
    }

    public Goods getProductById(int id) {
        Optional<Goods> optionalProduct = goodsRepository.findById(id);
        Goods goods = null;
        if (optionalProduct.isPresent()) {
            goods = optionalProduct.get();
        } else {
            throw new RuntimeException("Product not found for id : " + id);
        }
        return goods;
    }

    public void deleteProductById(int id) {
        this.goodsRepository.deleteById(id);
    }
}




