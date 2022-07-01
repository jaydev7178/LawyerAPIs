package com.example.lawyers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LawType {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name; 
    private String icon;
    private boolean deleted;
    private boolean status;
    public LawType() {
    }
    public LawType(int id, String name, String icon, boolean deleted, boolean status) {
        this.id = id;
        this.name = name;
        this.icon = icon;
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

    
    
}
