package com.example.lawyers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class LawyerLawSubtypeMapping {
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int lawyerId;
    private int lawSubtypeId;
    @Transient
    private String lawSubtypeName;
    private boolean deleted;
    private boolean status;
    public LawyerLawSubtypeMapping() {
    }
    public LawyerLawSubtypeMapping(int id, int lawyerId, int lawSubtypeId, boolean deleted, boolean status) {
        this.id = id;
        this.lawyerId = lawyerId;
        this.lawSubtypeId = lawSubtypeId;
        this.deleted = deleted;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLawyerId() {
        return lawyerId;
    }
    public void setLawyerId(int lawyerId) {
        this.lawyerId = lawyerId;
    }
    public int getLawSubtypeId() {
        return lawSubtypeId;
    }
    public void setLawSubtypeId(int lawSubtypeId) {
        this.lawSubtypeId = lawSubtypeId;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getLawSubtypeName() {
        return lawSubtypeName;
    }
    public void setLawSubtypeName(String lawSubtypeName) {
        this.lawSubtypeName = lawSubtypeName;
    }
    
    
    
}
