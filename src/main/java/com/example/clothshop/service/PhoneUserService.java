package com.example.clothshop.service;


import com.example.clothshop.model.PhoneUser;
import com.example.clothshop.model.User;
import com.example.clothshop.repository.PhoneUserRepository;
import com.example.clothshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PhoneUserService {

    @Autowired
    private PhoneUserRepository phoneUserRepository;

    public PhoneUserService(PhoneUserRepository phoneUserRepository) {
        this.phoneUserRepository = phoneUserRepository;
    }

    public List<PhoneUser> getAllPhoneUser() {
        return phoneUserRepository.findAll();
    }

    public void savePhoneUser(PhoneUser phoneUser) {
        this.phoneUserRepository.save(phoneUser);
    }

    public PhoneUser getPhoneUserById(int id) {
        Optional<PhoneUser> optionalPhoneUser = phoneUserRepository.findById(id);
        PhoneUser phoneUser = null;
        if (optionalPhoneUser.isPresent()) {
            phoneUser = optionalPhoneUser.get();
        } else {
            throw new RuntimeException("User not found for id : " + id);
        }
        return phoneUser;
    }

    public void deletePhoneUserById(int id) {
        this.phoneUserRepository.deleteById(id);
    }
}
