package com.example.clothshop.repository;

import com.example.clothshop.model.AddressUser;
import com.example.clothshop.model.CategoryGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressUserRepository extends JpaRepository<AddressUser, Integer> {
}
