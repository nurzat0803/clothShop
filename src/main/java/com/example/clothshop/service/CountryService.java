package com.example.clothshop.service;

import com.example.clothshop.model.Country;
import com.example.clothshop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public void saveCountry(Country country) {
        this.countryRepository.save(country);
    }

    public Country getCountryById(int id) {
        Optional<Country> optionalProduct = countryRepository.findById(id);
        Country country = null;
        if (optionalProduct.isPresent()) {
            country = optionalProduct.get();
        } else {
            throw new RuntimeException("Product not found for id : " + id);
        }
        return country;
    }

    public void deleteCountryById(int id) {
        this.countryRepository.deleteById(id);
    }
}

