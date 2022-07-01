package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.LawSubtype;
import com.example.lawyers.repository.LawSubtypeRepository;

@Service
public class LawSubtypeService {
    @Autowired
    private LawSubtypeRepository repo;

    public List<LawSubtype> getLawSubtypeList()
    {
        return repo.findAll();
    }
    public LawSubtype getLawSubtypeById(int id)
    {
        return repo.findById(id).get();
    }
    // public List<LawSubtype> getLawSubtypeByLawTypeId(int lawTypeId)
    // {
    //     return repo.findByLawTypeId(lawTypeId);
    // }
        

    public void saveLawSubtype(LawSubtype lawSubtype)
    {
        repo.save(lawSubtype);
    }
}
