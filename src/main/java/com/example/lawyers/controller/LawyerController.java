package com.example.lawyers.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.lawyers.model.JwtToken;
import com.example.lawyers.model.Lawyer;
import com.example.lawyers.model.Login;
import com.example.lawyers.model.ReturnObj;
import com.example.lawyers.repository.LawyerRepository;
import com.example.lawyers.service.LawyerService;

@Controller
@RequestMapping("api/lawyer/")
public class LawyerController {
    
    @Autowired
    private LawyerService service;
    @Autowired
    private LawyerRepository repo;
    
    @PostMapping("register")
    public ResponseEntity<ReturnObj> registerLawyer(@RequestBody Lawyer lawyer)
    {
        try {
            if(lawyer.getEmail()==null ||lawyer.getMobile()=="")
			{
				return ReturnObj.returnHttp("201","Please enter email." );
			}
			
			if(lawyer.getPassword()==null ||lawyer.getPassword()=="")
			{
				return ReturnObj.returnHttp("201","Please enter Password." );
			}
			
			if(lawyer.getMobile()==null ||lawyer.getMobile()=="")
			{
				return ReturnObj.returnHttp("201","Please enter Mobile." );
			}
			
			if(lawyer.getName()==null ||lawyer.getName()=="")
			{
				return ReturnObj.returnHttp("201","Please enter Name." );
			}
			if(lawyer.getDob()!=null ||lawyer.getDob()!="")
			{
				LocalDate dob = LocalDate.parse(lawyer.getDob());
				LocalDate curDate = LocalDate.now();  
				if(Period.between(dob, curDate).getYears()<18)
				{
					return ReturnObj.returnHttp("201","You can't Register because your age is below 18." );
				}
			}else
            {
                return ReturnObj.returnHttp("201","Please enter Date of birth." );
            }
			if(lawyer.getEmail().isEmpty() || lawyer.getEmail().isBlank())
			{
				return ReturnObj.returnHttp("201","Please enter email." );
			}
			if(lawyer.getLicenseNo().isEmpty() || lawyer.getLicenseNo().isBlank())
			{
				return ReturnObj.returnHttp("201","Please enter LicenseNo." );
			}
            lawyer.setDeleted(false);
			lawyer.setStatus(true);
			
            service.saveLawyer(lawyer);
            return ReturnObj.returnHttp("200", "Lawyer Registered successfully");
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
        }
    }
    
    @PostMapping("login")
    public ResponseEntity<ReturnObj> login(@RequestBody Login login)
    {
        try {
            if(login.getUsername()==null || login.getUsername()=="")
            {
                return ReturnObj.returnHttp("201","Please enter Username ." );
            }
            
            if(login.getPassword()==null ||login.getPassword()=="")
            {
                return ReturnObj.returnHttp("201","Please enter Password." );
            }       
            Lawyer lawyer=repo.findByEmail(login.getUsername());
            if(lawyer==null)
            {
                return ReturnObj.returnHttp("201","Invalid Username." );
            }
            if(!lawyer.getPassword().equals(login.getPassword()))
            {
                return ReturnObj.returnHttp("201","Invalid credential, Please try with proper credential." );
            }
            lawyer.setPassword(null);
            
            return ReturnObj.returnHttp("200", JwtToken.genrateToken(lawyer.getId(),"lawyer"));
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage());    
        }
    }

    @PostMapping("getLawyerList")
    public ResponseEntity<ReturnObj> getLawyerList(@RequestHeader("token") String token,  @RequestBody Lawyer lawyer)
    {

        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {

            if(lawyer.getId()==0)
            {
                return ReturnObj.returnHttp("200", service.getLawyerList());    
            }else if(lawyer.getId()>0)
            {
                return ReturnObj.returnHttp("200", service.getLawyerById(lawyer.getId()));    
            }else
            {
                return ReturnObj.returnHttp("201", "Some internal issue occured, please try again.");    
            }
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
            //TODO: handle exception
        }
    } 
    @PostMapping("getProfile")
    public ResponseEntity<ReturnObj> getLawyerList(@RequestHeader("token") String token)
    {

        JwtToken output= JwtToken.validateToken(token,"lawyer");
        if(output.getError()!=null)
        {
            return ReturnObj.returnHttp("401", output.getError());     
        }
        try {
            Lawyer lawyer=service.getLawyerById(output.getId());
            lawyer.setId(0);
            lawyer.setPassword(null);
            return ReturnObj.returnHttp("200",lawyer );    
        } catch (Exception e) {
            return ReturnObj.returnHttp("201", e.getMessage()+" "+e.getStackTrace());    
            //TODO: handle exception
        }
    }
}
