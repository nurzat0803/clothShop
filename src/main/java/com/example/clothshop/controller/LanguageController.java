package com.example.clothshop.controller;


import com.example.clothshop.exception.ResourceNotFoundException;
import com.example.clothshop.model.Language;
import com.example.clothshop.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("/language")
    public List<Language> getAllLanguage(){
        return languageRepository.findAll();
    }


    // create employee rest api
    @PostMapping("/language")
    public Language createLanguage(@RequestBody Language language) {
        return languageRepository.save(language);
    }

    // get employee by id rest api
    @GetMapping("/language/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id) throws ResourceNotFoundException {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language not exist with id :" + id));
        return ResponseEntity.ok(language);
    }

    // update employee rest api

    @PutMapping("/language/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Integer id,
                                                           @RequestBody Language languageDetails) throws ResourceNotFoundException {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language not exist with id :" + id));

        language.setShort_name(languageDetails.getShort_name());
        language.setFull_name(languageDetails.getFull_name());
        language.setCategoryGoodList(languageDetails.getCategoryGoodList());
        language.setStatusList(languageDetails.getStatusList());

        Language updateLanguage = languageRepository.save(language);
        return ResponseEntity.ok(updateLanguage);
    }

    // delete employee rest api
    @DeleteMapping("/language/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLanguage(@PathVariable Integer id) throws ResourceNotFoundException {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language not exist with id :" + id));

        languageRepository.delete(language);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}