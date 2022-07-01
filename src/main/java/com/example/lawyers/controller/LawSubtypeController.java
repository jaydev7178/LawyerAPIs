package com.example.lawyers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.lawyers.model.JwtToken;
import com.example.lawyers.model.LawSubtype;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.service.LawSubtypeService;
import com.example.lawyers.service.LawTypeService;

@Controller
public class LawSubtypeController {
    @Autowired
    private LawSubtypeService service;
    
    @PostMapping("saveLawSubtype")
    public ResponseEntity<ReturnObj> saveLawSubtype(@RequestHeader("token") String token,@RequestBody LawSubtype lawSubtype)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(lawSubtype.getLawTypeId()>0 )
            {
                return ReturnObj.returnHttp("201","Please enter name of law type." );
            }
            
            if(lawSubtype.getName().isEmpty() || lawSubtype.getName().isBlank())
            {
                return ReturnObj.returnHttp("201","Please enter name of law type." );
            }

            lawSubtype.setDeleted(false);
			lawSubtype.setStatus(true);
            service.saveLawSubtype(lawSubtype);

            return ReturnObj.returnHttp("200", "Law Type saved Sucessfully.");
            
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());      
            //TODO: handle exception
        }
    }
    @PostMapping("getLawSubtypeList")
    public ResponseEntity<ReturnObj> getLawSubtypeList(@RequestHeader("token") String token,  @RequestBody LawSubtype lawSubtype)
    {
        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            if(lawSubtype.getId()==0)
            {
                return ReturnObj.returnHttp("200", service.getLawSubtypeList());    
            }else if(lawSubtype.getId()>0)
            {
                return ReturnObj.returnHttp("200", service.getLawSubtypeById(lawSubtype.getId()));    
            }else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }

        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
        }
    }
    // @PostMapping("getLawSubtypeByLawTypeIdList")
    // public ResponseEntity<ReturnObj> getLawSubtypeByLawTypeIdList(@RequestHeader("token") String token,  @RequestBody LawSubtype lawSubtype)
    // {
    //     JwtToken output= JwtToken.validateToken(token,"lawyer");
    //     if(output.getError()!=null)
    //     {
    //         return ReturnObj.returnHttp("401", output.getError());     
    //     }
    //     try {

    //         if(lawSubtype.getLawTypeId()>0)
    //         {
    //             return ReturnObj.returnHttp("200", service.getLawSubtypeByLawTypeId(lawSubtype.getLawTypeId()));    
    //         }else
    //         {
    //             return ReturnObj.returnHttp("201", "Please Select Law Type.");    
    //         }

    //     } catch (Exception e) {
    //         return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
    //     }
    // }
    
}
