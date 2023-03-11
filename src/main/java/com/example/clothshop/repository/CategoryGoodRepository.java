package com.example.clothshop.repository;


import com.example.clothshop.model.CategoryGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryGoodRepository extends JpaRepository<CategoryGood, Integer> {
}
