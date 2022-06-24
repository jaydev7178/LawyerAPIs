package com.example.lawyers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.City;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.service.CityService;

@Controller
@RequestMapping("api/city/")
public class CityController {
    
    @Autowired
    private CityService service; 
    @PostMapping("saveCity")
    public ResponseEntity<ReturnObj> saveCity(@RequestBody City city)
    {
        try {
            service.saveCity(city);
            return ReturnObj.returnHttp("200", "City saved successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
        }
    }
    
    @PostMapping("getCityList")
    public ResponseEntity<ReturnObj> getStateList()
    {
        try {
            return ReturnObj.returnHttp("200", service.getCityList());
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        }
    }
    
    @PostMapping("deleteCity")
    public ResponseEntity<ReturnObj> deleteCity(@RequestBody City dataString)
    {
        try {
            service.deleteCity(dataString.getId());
            return ReturnObj.returnHttp("200", "State deleted successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        }
    }
}
