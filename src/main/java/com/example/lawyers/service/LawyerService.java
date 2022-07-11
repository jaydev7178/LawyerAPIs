package com.example.lawyers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.City;
import com.example.lawyers.model.Country;
import com.example.lawyers.model.Lawyer;
import com.example.lawyers.model.State;
import com.example.lawyers.repository.CityRepository;
import com.example.lawyers.repository.CountryRepository;
import com.example.lawyers.repository.LawyerRepository;
import com.example.lawyers.repository.StateRepository;

@Service
public class LawyerService {
    @Autowired
    private LawyerRepository repo;
    @Autowired
    private StateRepository staterepo;
    @Autowired
    private CountryRepository countryrepo;
    @Autowired
    private CityRepository cityrepo;

    public void saveLawyer(Lawyer lawyer)
    {
        repo.save(lawyer);
    }

    public  List<Lawyer> getLawyerList()
    {
        List<Lawyer> lawyerList= repo.findAll();
        for (Lawyer lawyer : lawyerList) {
            City city=cityrepo.findById(lawyer.getCity_id()).get();
            State state=staterepo.findById(city.getStateId()).get();
            Country country=countryrepo.findById(state.getCountryId()).get();
            lawyer.setCityName(city.getName());
            lawyer.setStateName(state.getName());
            lawyer.setCountryName(country.getName());
        }
        return lawyerList;
    }
    
    public Lawyer getLawyerById(int id)
    {
        List<Lawyer> lawyerList=repo.findById(id);
        for (Lawyer lawyer : lawyerList) {
            City city=cityrepo.findById(lawyer.getCity_id()).get();
            State state=staterepo.findById(city.getStateId()).get();
            Country country=countryrepo.findById(state.getCountryId()).get();
            lawyer.setCityName(city.getName());
            lawyer.setStateName(state.getName());
            lawyer.setCountryName(country.getName());
        }
        return lawyerList.get(0);
    }
    
    public Lawyer findByIdAndDeleted(int id, boolean deleted)
    {
        List<Lawyer> lawyerList=repo.findByIdAndDeleted(id, false);
        Lawyer lawyer=new Lawyer();
        if(lawyerList.get(0)==null)
            return lawyer;
        return lawyer=lawyerList.get(0);
    }


    public void deletelawyer(Lawyer lawyer)
    {
        lawyer.setDeleted(true);
        repo.save(lawyer);        
    }
}
