package com.example.clothshop.service;


import com.example.clothshop.model.AddressUser;
import com.example.clothshop.model.CategoryGood;
import com.example.clothshop.repository.AddressUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
public class AddressUserService {

    @Autowired
    private AddressUserRepository addressUserRepository;

    public AddressUserService(AddressUserRepository addressUserRepository) {
        this.addressUserRepository = addressUserRepository;
    }

    public List<AddressUser> getAddressUser() {
        return addressUserRepository.findAll();
    }

    public AddressUser saveAddressUser(AddressUser addressUser) {
        this.addressUserRepository.save(addressUser);
        return addressUser;
    }

    public AddressUser getAddressUserById(int id) {
        Optional<AddressUser> optionalCategory = addressUserRepository.findById(id);
        AddressUser addressUser = null;
        if (optionalCategory.isPresent()) {
            addressUser = optionalCategory.get();
        } else {
            throw new RuntimeException("Category not found for id : " + id);
        }
        return addressUser;
    }

    public AddressUser deleteAddressUserById(int id) {
        this.addressUserRepository.deleteById(id);
        return null;
    }
}
