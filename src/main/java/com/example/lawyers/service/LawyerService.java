package com.example.lawyers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.Lawyer;
import com.example.lawyers.repository.LawyerRepository;

@Service
public class LawyerService {
    @Autowired
    private LawyerRepository repo;

    public void saveLawyer(Lawyer lawyer)
    {
        repo.save(lawyer);
    }

    public  List<Lawyer> getLawyerList()
    {
        return repo.findAll();
    }
    
    public Lawyer getLawyerById(int id)
    {
        Optional<Lawyer> lawyer=repo.findById(id);
        return lawyer.get();
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
