package com.example.lawyers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.JwtToken;
import com.example.lawyers.model.LawyerLawSubtypeMapping;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.service.LawyerLawSubtypeMappingService;

@Controller
@RequestMapping("api/lawyerLawSubtypeMapping/")
public class LawyerLawSubtypeMappingController {
    @Autowired
    private LawyerLawSubtypeMappingService service;
    
    @PostMapping("saveLawyerLawSubtypeMapping")
    public ResponseEntity<ReturnObj> saveLawyerLawSubtypeMapping(@RequestHeader("token") String token,@RequestBody LawyerLawSubtypeMapping lawyerLawSubtypeMapping)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }          
        try {
            if(lawyerLawSubtypeMapping.getLawSubtypeId()<=0 )
            {
                return ReturnObj.returnHttp("201","Please select law type." );
            }
            lawyerLawSubtypeMapping.setLawyerId(output.getId());
            lawyerLawSubtypeMapping.setDeleted(false);
			lawyerLawSubtypeMapping.setStatus(true);
            service.saveLawyerLawSubtypeMapping(lawyerLawSubtypeMapping);

            return ReturnObj.returnHttp("200", "Law Type saved Sucessfully.");
            
        } catch (Exception e) {
            //TODO: handle exception
            if(e.getMessage().contains("UK_lawyer_lawSubtype")|| e.getStackTrace().toString().contains("UK_lawyer_lawSubtype"))
                return ReturnObj.returnHttp("201", "Law subtype is already added.");          
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());      
        }
    }

    @PostMapping("saveLawyerLawSubtypeMappingInBulk")
    public ResponseEntity<ReturnObj> saveLawyerLawSubtypeMappingInBulk(@RequestHeader("token") String token,@RequestBody ArrayList<LawyerLawSubtypeMapping> lawyerLawSubtypeMappingList)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }          
        try {
            if(lawyerLawSubtypeMappingList.size()>0)
            {
                for (LawyerLawSubtypeMapping lawyerLawSubtypeMapping2 : lawyerLawSubtypeMappingList) {
                    if(lawyerLawSubtypeMapping2.getLawSubtypeId()<=0 )
                {
                    continue;
                    //return ReturnObj.returnHttp("201","Please select law type." );
                }
                lawyerLawSubtypeMapping2.setLawyerId(output.getId());
                lawyerLawSubtypeMapping2.setDeleted(false);
                lawyerLawSubtypeMapping2.setStatus(true);
                service.saveLawyerLawSubtypeMapping(lawyerLawSubtypeMapping2);
        
                }
            }
            
            return ReturnObj.returnHttp("200", "Law Type saved Sucessfully.");
            
        } catch (Exception e) {
            //TODO: handle exception
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());      
        }
    }

    @PostMapping("getLawyerLawSubtypeMappingList")
    public ResponseEntity<ReturnObj> getLawyerLawSubtypeMappingList(@RequestHeader("token") String token,  @RequestBody LawyerLawSubtypeMapping lawyerLawSubtypeMapping)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(output.getId()>0)
                
                return ReturnObj.returnHttp("200", service.getLawyerLawSubtypeMappingByLawyerId(output.getId()));    
            else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }

        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
        }
    }

    @PostMapping("deleteLawyerLawSubtypeMappingById")
    public ResponseEntity<ReturnObj> deleteLawyerLawSubtypeMappingById(@RequestHeader("token") String token,  @RequestBody LawyerLawSubtypeMapping lawyerLawSubtypeMapping)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(lawyerLawSubtypeMapping.getId()>0){
                service.deleteLawyerLawSubtypeMappingById(lawyerLawSubtypeMapping.getId());
                return ReturnObj.returnHttp("200", "Law Subtype is removed.");    
            }
            else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }

        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
        }
    }
    
}
