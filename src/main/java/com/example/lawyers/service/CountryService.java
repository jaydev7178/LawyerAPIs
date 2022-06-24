package com.example.lawyers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.Country;
import com.example.lawyers.repository.CountryRepository;

@Service
public class CountryService {
    
    @Autowired
    private CountryRepository repo;

    public List<Country> getCountryList()
    {
        return repo.findAll();
    }
    
    public Country getCountryById(int id)
    {
        return repo.findById(id).get();
    }

    public void saveCountry(Country country)
    {
        repo.save(country);
    }

      
}
