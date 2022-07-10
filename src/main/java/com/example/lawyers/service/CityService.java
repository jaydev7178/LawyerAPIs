package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.City;
import com.example.lawyers.model.Country;
import com.example.lawyers.model.State;
import com.example.lawyers.repository.CityRepository;
import com.example.lawyers.repository.CountryRepository;
import com.example.lawyers.repository.StateRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository repo;
    
    @Autowired
    private StateRepository staterepo;
    @Autowired
    private CountryRepository countryrepo;

    public void saveCity(City city)
    {
        repo.save(city);
    }

    public List<City> getCityList()
    {
        List<City> cityList=repo.findAll();
        for (City city : cityList) {
            State state=staterepo.findById(city.getStateId()).get();
            Country country=countryrepo.findById(state.getCountryId()).get();
            city.setStateName(state.getName());
            city.setCountryName(country.getName());
        }
        return cityList;  
    }
    
    public List<City> getCityListByStateId(int id)
    {
        List<City> cityList=repo.findBystateId(id);
        for (City city : cityList) {
            State state=staterepo.findById(city.getStateId()).get();
            Country country=countryrepo.findById(state.getCountryId()).get();
            city.setStateName(state.getName());
            city.setCountryName(country.getName());
        }
        return cityList;  
    }
    
    
    public City getCityListById(int id)
    {
        List<City> cityList=repo.findBystateId(id);
        State state=staterepo.findById(cityList.get(0).getStateId()).get();
            Country country=countryrepo.findById(state.getCountryId()).get();
            cityList.get(0).setStateName(state.getName());
            cityList.get(0).setCountryName(country.getName());
        return cityList.get(0);  
    }


    public void deleteCity(int id)
    {
        repo.deleteById(id);
    }
    
}
