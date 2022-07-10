package com.example.lawyers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class LawSubtype {
    
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name; 
    private String icon;
    private String description;
    private int lawTypeId;
    private boolean deleted;
    private boolean status;
    public LawSubtype() {
    }
    public LawSubtype(int id, String name, String icon, int lawTypeId, boolean deleted, boolean status) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.lawTypeId = lawTypeId;
        this.deleted = deleted;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getLawTypeId() {
        return lawTypeId;
    }
    public void setLawTypeId(int lawTypeId) {
        this.lawTypeId = lawTypeId;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
    
}
