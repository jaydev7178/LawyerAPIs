package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.LawyerLawSubtypeMapping;
import com.example.lawyers.repository.LawyerLawSubtypeMappingRepository;

@Service
public class LawyerLawSubtypeMappingService {
    @Autowired
    private LawyerLawSubtypeMappingRepository repo;

    public List<LawyerLawSubtypeMapping> getLawyerLawSubtypeMappingList()
    {
        return repo.findAll();
    }
    public LawyerLawSubtypeMapping getLawyerLawSubtypeMappingById(int id)
    {
        return repo.findById(id).get();
    }
    public LawyerLawSubtypeMapping getLawyerLawSubtypeMappingByLawyerId(int id)
    {
        return repo.findByLawyerId(id);
    }
    public LawyerLawSubtypeMapping getLawyerLawSubtypeMappingByLawSubtypeId(int id)
    {
        return repo.findByLawSubtypeId(id);
    }
    
    public void saveLawyerLawSubtypeMapping(LawyerLawSubtypeMapping lawSubtype)
    {
        repo.save(lawSubtype);
    }
}
