package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.Goods;
import com.example.clothshop.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("/goods")
    public List<Goods> getAllGoods(){
        return goodsRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/goods")
    public Goods createGoods(@RequestBody Goods goods) {
        return goodsRepository.save(goods);
    }

    // get employee by id rest api
    @GetMapping("/goods/{id}")
    public ResponseEntity<Goods> getGoodsById(@PathVariable Integer id) throws ResourceNotFoundException {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("goods not exist with id :" + id));
        return ResponseEntity.ok(goods);
    }

    // update employee rest api

    @PutMapping("/goods/{id}")
    public ResponseEntity<Goods> updateGoods(@PathVariable Integer id,
                                                           @RequestBody Goods goodsDetails) throws ResourceNotFoundException {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("goods not exist with id :" + id));

        goods.setName(goodsDetails.getName());
        goods.setDescription(goodsDetails.getDescription());
        goods.setCost(goodsDetails.getCost());
        goods.setIs_active(goodsDetails.getIs_active());
        goods.setCategory_id(goodsDetails.getCategory_id());
        goods.setCountry_id(goodsDetails.getCountry_id());

        Goods updateGoods = goodsRepository.save(goods);
        return ResponseEntity.ok(updateGoods);
    }

    // delete employee rest api
    @DeleteMapping("/goods/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGoods(@PathVariable Integer id) throws ResourceNotFoundException {
        Goods goods = goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("goods not exist with id :" + id));

        goodsRepository.delete(goods);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
