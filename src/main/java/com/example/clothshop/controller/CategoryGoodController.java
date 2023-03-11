package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.CategoryGood;
import com.example.clothshop.repository.CategoryGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class CategoryGoodController {

    @Autowired
    private CategoryGoodRepository categoryGoodRepository;

    @GetMapping("/category_good")
    public List<CategoryGood> getAllCategoryGood(){
        return categoryGoodRepository.findAll();
    }


    // create employee rest api
    @PostMapping("/category_good")
    public CategoryGood createCategoryGood(@RequestBody CategoryGood categoryGood) {
        return categoryGoodRepository.save(categoryGood);
    }

    // get employee by id rest api
    @GetMapping("/category_good/{id}")
    public ResponseEntity<CategoryGood> getCategoryGoodById(@PathVariable Integer id) throws ResourceNotFoundException {
        CategoryGood categoryGood = categoryGoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryGood not exist with id :" + id));
        return ResponseEntity.ok(categoryGood);
    }

    // update employee rest api
    @PutMapping("/category_good/{id}")
    public ResponseEntity<CategoryGood> updateCategoryGood(@PathVariable Integer id,
                                                   @RequestBody CategoryGood categoryGoodDetails) throws ResourceNotFoundException {
        CategoryGood categoryGood = categoryGoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryGood not exist with id :" + id));

        categoryGood.setName(categoryGoodDetails.getName());
        categoryGood.setLanguages(categoryGoodDetails.getLanguages());
        categoryGood.setGoods(categoryGoodDetails.getGoods());

        CategoryGood updateCategoryGood = categoryGoodRepository.save(categoryGood);
        return ResponseEntity.ok(updateCategoryGood);
    }

    // delete employee rest api
    @DeleteMapping("/category_good/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategoryGood(@PathVariable Integer id) throws ResourceNotFoundException {
        CategoryGood categoryGood = categoryGoodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CategoryGood not exist with id :" + id));

        categoryGoodRepository.delete(categoryGood);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}