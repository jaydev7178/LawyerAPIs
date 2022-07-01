package com.example.lawyers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.JwtToken;
import com.example.lawyers.model.LawType;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.service.LawTypeService;
import com.example.lawyers.service.LawyerService;

@Controller
@RequestMapping("api/lawType/")
public class LawTypeController {
    @Autowired
    private LawTypeService service;
    
    @PostMapping("saveLawType")
    public ResponseEntity<ReturnObj> saveLawType(@RequestHeader("token") String token,@RequestBody LawType lawType)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(lawType.getName().isEmpty() || lawType.getName().isBlank())
            {
                return ReturnObj.returnHttp("201","Please enter name of law type." );
            }
            lawType.setDeleted(false);
			lawType.setStatus(true);
            service.saveLawType(lawType);

            return ReturnObj.returnHttp("200", "Law Type saved Sucessfully.");
        }catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
            //TODO: handle exception
        } 
    }


    @PostMapping("getLawTypeList")
    public ResponseEntity<ReturnObj> getLawTypeList(@RequestHeader("token") String token,  @RequestBody LawType lawType)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(lawType.getId()==0)
            {
                return ReturnObj.returnHttp("200", service.getLawTypeList());    
            }else if(lawType.getId()>0)
            {
                return ReturnObj.returnHttp("200", service.getLawTypeById(lawType.getId()));    
            }else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }

        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
        }
    }
}
