package com.example.lawyers.controller;

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
            if(lawyerLawSubtypeMapping.getId()==0)
            {
                return ReturnObj.returnHttp("200", service.getLawyerLawSubtypeMappingList());    
            }else if(lawyerLawSubtypeMapping.getLawyerId()>0)
            {
                return ReturnObj.returnHttp("200", service.getLawyerLawSubtypeMappingByLawyerId(lawyerLawSubtypeMapping.getLawyerId()));    
            }else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }

        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
        }
    }
}
