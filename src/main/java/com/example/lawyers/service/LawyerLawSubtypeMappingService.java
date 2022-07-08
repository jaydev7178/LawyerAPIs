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
    @Autowired
    private LawSubtypeService lawSubtypeService;

    public List<LawyerLawSubtypeMapping> getLawyerLawSubtypeMappingList()
    {
        return repo.findAll();
    }
    public LawyerLawSubtypeMapping getLawyerLawSubtypeMappingById(int id)
    {
        return repo.findById(id).get();
    }
    public List<LawyerLawSubtypeMapping> getLawyerLawSubtypeMappingByLawyerId(int id)
    {   List<LawyerLawSubtypeMapping>lawyerLawSubtypeMappingList =repo.findByLawyerId(id);
        //lawSubtypeService=new LawSubtypeService();
        for (LawyerLawSubtypeMapping lawyerLawSubtypeMapping : lawyerLawSubtypeMappingList) {
            lawyerLawSubtypeMapping.setLawSubtypeName(lawSubtypeService.getLawSubtypeById(lawyerLawSubtypeMapping.getLawSubtypeId()).getName());
            // System.out.println("id is "+lawyerLawSubtypeMapping.getLawSubtypeId());
            // System.out.println(lawSubtypeService.getLawSubtypeById(lawyerLawSubtypeMapping.getLawSubtypeId()).getName());
        }
        return lawyerLawSubtypeMappingList;
    }
    public LawyerLawSubtypeMapping getLawyerLawSubtypeMappingByLawSubtypeId(int id)
    {
        return repo.findByLawSubtypeId(id);
    }
    
    public void saveLawyerLawSubtypeMapping(LawyerLawSubtypeMapping lawSubtype)
    {
        repo.save(lawSubtype);
    }

    public void deleteLawyerLawSubtypeMappingById(int id)
    {
        repo.deleteById(id);
    }
}
