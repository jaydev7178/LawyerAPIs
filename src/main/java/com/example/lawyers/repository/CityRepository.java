package com.example.lawyers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.City;

@Repository
public interface CityRepository  extends JpaRepository<City, Integer>{
    
}
