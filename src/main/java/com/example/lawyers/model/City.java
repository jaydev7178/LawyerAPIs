package com.example.lawyers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int stateId;
    private String name; 
    private boolean deleted;
    private boolean status;
    public City(int id, int stateId, String name, boolean deleted, boolean status) {
        this.id = id;
        this.stateId = stateId;
        this.name = name;
        this.deleted = deleted;
        this.status = status;
    }
    public City() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStateId() {
        return stateId;
    }
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
