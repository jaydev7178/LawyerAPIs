package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.City;

@Repository
public interface CityRepository  extends JpaRepository<City, Integer>{
    public List<City> findBystateId(int id);
}
