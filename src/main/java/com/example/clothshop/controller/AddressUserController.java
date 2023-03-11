package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.AddressUser;
import com.example.clothshop.repository.AddressUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class AddressUserController {

    @Autowired
    private AddressUserRepository addressUserRepository;

    @GetMapping("/address_user")
    public List<AddressUser> getAllAddressUser(){
        return addressUserRepository.findAll();
    }


    // create employee rest api
    @PostMapping("/address_user")
    public AddressUser createAddressUser(@RequestBody AddressUser addressUser) {
        return addressUserRepository.save(addressUser);
    }

    // get employee by id rest api
    @GetMapping("/address_user/{id}")
    public ResponseEntity<AddressUser> getAddressUserById(@PathVariable Integer id) throws ResourceNotFoundException {
        AddressUser addressUser = addressUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("addressUser not exist with id :" + id));
        return ResponseEntity.ok(addressUser);
    }

    // update employee rest api

    @PutMapping("/address_user/{id}")
    public ResponseEntity<AddressUser> updateAddressUser(@PathVariable Integer id,
                                                           @RequestBody AddressUser addressUserDetails) throws ResourceNotFoundException {
        AddressUser addressUser = addressUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("addressUser not exist with id :" + id));

        addressUser.setUser_id(addressUserDetails.getUser_id());
        addressUser.setAddress(addressUserDetails.getAddress());

        AddressUser updateAddressUser = addressUserRepository.save(addressUser);
        return ResponseEntity.ok(updateAddressUser);
    }

    // delete employee rest api
    @DeleteMapping("/address_user/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAddressUser(@PathVariable Integer id) throws ResourceNotFoundException {
        AddressUser addressUser = addressUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("addressUser not exist with id :" + id));

        addressUserRepository.delete(addressUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
