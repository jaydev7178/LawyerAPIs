package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.Country;
import com.example.lawyers.model.State;
import com.example.lawyers.repository.CountryRepository;
import com.example.lawyers.repository.StateRepository;

@Service
public class StateService {
    @Autowired
    private StateRepository repo;
    
    @Autowired
    private  CountryRepository countryrepo;

    public void saveState(State state)
    {
        repo.save(state);
    }

    public List<State> getStateList()
    {
        List<State> stateList=repo.findAll();
        for (State state : stateList) {
            Country country=countryrepo.findById(state.getCountryId()).get();
            state.setCountryName(country.getName());
        }
        return stateList; 
    }
    
    public List<State> getStateListByCountryId(int id)
    {
        List<State> stateList=repo.findByCountryId(id);
        for (State state : stateList) {
            Country country=countryrepo.findById(state.getCountryId()).get();
            state.setCountryName(country.getName());
        }
        return stateList; 
    }

    public State getStateById(int id)
    {
        State state=repo.findById(id).get();
        Country country=countryrepo.findById(state.getCountryId()).get();
        state.setCountryName(country.getName());
        return state; 
    }
    
    public void deleteState(int id)
    {
        repo.deleteById(id);
    }
}