package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.City;
import com.example.lawyers.repository.CityRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository repo;

    public void saveCity(City city)
    {
        repo.save(city);
    }

    public List<City> getCityList()
    {
        List<City> cityList=repo.findAll();
        return cityList;  
    }
    public void deleteCity(int id)
    {
        repo.deleteById(id);
    }
    
}
