package com.example.clothshop.repository;

import com.example.clothshop.model.PhoneUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneUserRepository extends JpaRepository<PhoneUser, Integer> {
}
