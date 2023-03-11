package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.Status;
import com.example.clothshop.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/status")
    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/status")
    public Status createStatus(@RequestBody Status status) {
        return statusRepository.save(status);
    }

    // get employee by id rest api
    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Integer id) throws ResourceNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("status not exist with id :" + id));
        return ResponseEntity.ok(status);
    }

    // update employee rest api
    @PutMapping("/status/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Integer id,
                                                     @RequestBody Status statusDetails) throws ResourceNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("status not exist with id :" + id));

        status.setName(statusDetails.getName());
        status.setLanguage_id(statusDetails.getLanguage_id());

        Status updateStatus = statusRepository.save(status);
        return ResponseEntity.ok(updateStatus);
    }

    // delete employee rest api
    @DeleteMapping("/status/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStatus(@PathVariable Integer id) throws ResourceNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("status not exist with id :" + id));

        statusRepository.delete(status);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
