package com.example.lawyers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.Country;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.repository.CountryRepository;
import com.example.lawyers.service.CountryService;

@Controller
@RequestMapping("api/country/")
public class CountryController {
    
    @Autowired
    private CountryService service;

    @PostMapping("saveCountry")
    public ResponseEntity<ReturnObj> saveCountry(@RequestBody Country country)
    {
        try {
            service.saveCountry(country);
            return ReturnObj.returnHttp("200", "Country saved successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        } 
        

    }
    
    @PostMapping("getCountryList")
    public ResponseEntity<ReturnObj> getCountryList()
    {
        try {
            List<Country> countryList=service.getCountryList(); 
            return ReturnObj.returnHttp("200", countryList);
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        } 
        

    }

    @PostMapping("getCountryById")
    public ResponseEntity<ReturnObj> getCountryById(@RequestBody Country dataString)
    {
        try {
            Country country=new Country(); 
            country=service.getCountryById(dataString.getId()); 
            return ReturnObj.returnHttp("200", country);
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        } 
        

    }
    

}
