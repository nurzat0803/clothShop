package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.PhoneUser;
import com.example.clothshop.repository.PhoneUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class PhoneUserController {

    @Autowired
    private PhoneUserRepository phoneUserRepository;

    @GetMapping("/phoneUser")
    public List<PhoneUser> getAllPhoneUser() {
        return phoneUserRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/phoneUser")
    public PhoneUser createPhoneUser(@RequestBody PhoneUser phoneUser) {
        return phoneUserRepository.save(phoneUser);
    }

    // get employee by id rest api
    @GetMapping("/phoneUser/{id}")
    public ResponseEntity<PhoneUser> getPhoneUserById(@PathVariable Integer id) throws ResourceNotFoundException {
        PhoneUser phoneUser = phoneUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("phoneUser not exist with id :" + id));
        return ResponseEntity.ok(phoneUser);
    }

    // update employee rest api
    @PutMapping("/phoneUser/{id}")
    public ResponseEntity<PhoneUser> updatePhoneUser(@PathVariable Integer id,
                                             @RequestBody PhoneUser phoneUserDetails) throws ResourceNotFoundException {
        PhoneUser phoneUser = phoneUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("phoneUser not exist with id :" + id));

        phoneUser.setUser_id(phoneUserDetails.getUser_id());
        phoneUser.setPhone_number(phoneUserDetails.getPhone_number());

        PhoneUser updatePhoneUser = phoneUserRepository.save(phoneUser);
        return ResponseEntity.ok(updatePhoneUser);
    }

    // delete employee rest api
    @DeleteMapping("/phoneUser/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePhoneUser(@PathVariable Integer id) throws ResourceNotFoundException {
        PhoneUser phoneUser = phoneUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("phoneUser not exist with id :" + id));

        phoneUserRepository.delete(phoneUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
