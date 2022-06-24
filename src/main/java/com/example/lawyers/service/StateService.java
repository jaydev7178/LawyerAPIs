package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.State;
import com.example.lawyers.repository.StateRepository;

@Service
public class StateService {
    @Autowired
    private StateRepository repo;

    public void saveState(State state)
    {
        repo.save(state);
    }

    public List<State> getStateList()
    {
        List<State> stateList=repo.findAll();
        return stateList; 
    }
    public State getStateById(int id)
    {
        State state=repo.findById(id).get();
        return state; 
    }
    
    public void deleteState(int id)
    {
        repo.deleteById(id);
    }
}