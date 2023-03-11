package com.example.clothshop.service;


import com.example.clothshop.model.Language;
import com.example.clothshop.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllProduct() {
        return languageRepository.findAll();
    }

    public void saveProduct(Language language) {
        this.languageRepository.save(language);
    }

    public Language getProductById(int id) {
        Optional<Language> optionalProduct = languageRepository.findById(id);
        Language language = null;
        if (optionalProduct.isPresent()) {
            language = optionalProduct.get();
        } else {
            throw new RuntimeException("Product not found for id : " + id);
        }
        return language;
    }

    public void deleteProductById(int id) {
        this.languageRepository.deleteById(id);
    }

}
