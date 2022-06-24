package com.example.lawyers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.model.State;
import com.example.lawyers.service.StateService;

@Controller
@RequestMapping("api/state/")
public class SateController {
    
    @Autowired
    private StateService service;
    
    @PostMapping("saveState")
    public ResponseEntity<ReturnObj> saveState(@RequestBody State dataString)
    {
        try {
            service.saveState(dataString);
            return ReturnObj.returnHttp("200", "State saved successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        } 
    }

    @PostMapping("getStateList")
    public ResponseEntity<ReturnObj> getStateList()
    {
        try {
            return ReturnObj.returnHttp("200", service.getStateList());
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        }
    }
    
    @PostMapping("deleteState")
    public ResponseEntity<ReturnObj> deleteState(@RequestBody State dataString)
    {
        try {
            service.deleteState(dataString.getId());
            return ReturnObj.returnHttp("200", "State deleted successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        }
    }
}
