package com.example.lawyers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.Country;

@Repository
public interface  CountryRepository  extends JpaRepository<Country, Integer> {
    
}
