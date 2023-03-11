package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.Country;
import com.example.clothshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/country")
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }


    // create employee rest api
    @PostMapping("/country")
    public Country createCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    // get employee by id rest api
    @GetMapping("/country/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id) throws ResourceNotFoundException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("country not exist with id :" + id));
        return ResponseEntity.ok(country);
    }

    // update employee rest api
    @PutMapping("/country/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id,
                                                           @RequestBody Country countryDetails) throws ResourceNotFoundException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("country not exist with id :" + id));

        country.setShort_name(countryDetails.getShort_name());
        country.setFull_name(countryDetails.getFull_name());
        country.setLanguage_id(countryDetails.getLanguage_id());
        country.setGoods(countryDetails.getGoods());

        Country updateCountry = countryRepository.save(country);
        return ResponseEntity.ok(updateCountry);
    }

    // delete employee rest api
    @DeleteMapping("/country/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCountry(@PathVariable Integer id) throws ResourceNotFoundException {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("country not exist with id :" + id));

        countryRepository.delete(country);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
