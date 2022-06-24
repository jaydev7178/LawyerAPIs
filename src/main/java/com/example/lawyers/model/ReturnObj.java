package com.example.lawyers.model;

import javax.persistence.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ReturnObj {
    private String code;
	private Object obj;
    public ReturnObj(String code, Object obj) {
        this.code = code;
        this.obj = obj;
    }
    public ReturnObj() {
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static ResponseEntity<ReturnObj>  returnHttp(String code, Object obj)
    {
        ReturnObj returnObj=new ReturnObj();
		returnObj.setCode(code);
		returnObj.setObj(obj);
		return new ResponseEntity<ReturnObj>(returnObj, HttpStatus.CREATED);
    }
        
}
