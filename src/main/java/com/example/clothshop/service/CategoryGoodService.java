package com.example.clothshop.service;


import com.example.clothshop.model.CategoryGood;
import com.example.clothshop.repository.CategoryGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryGoodService {
    @Autowired
    private CategoryGoodRepository categoryGoodRepository;

    public CategoryGoodService(CategoryGoodRepository categoryGoodRepository) {
        this.categoryGoodRepository = categoryGoodRepository;
    }

    public List<CategoryGood> getAllCategory() {
        return categoryGoodRepository.findAll();
    }

    public CategoryGood saveCategory(CategoryGood categoryGood) {
        this.categoryGoodRepository.save(categoryGood);
        return categoryGood;
    }

    public CategoryGood getCategoryById(int id) {
        Optional<CategoryGood> optionalCategory = categoryGoodRepository.findById(id);
        CategoryGood categoryGood = null;
        if (optionalCategory.isPresent()) {
            categoryGood = optionalCategory.get();
        } else {
            throw new RuntimeException("Category not found for id : " + id);
        }
        return categoryGood;
    }

    public CategoryGood deleteCategoryById(int id) {
        this.categoryGoodRepository.deleteById(id);
        return null;
    }

}