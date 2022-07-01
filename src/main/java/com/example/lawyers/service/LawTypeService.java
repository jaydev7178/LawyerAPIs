package com.example.lawyers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lawyers.model.LawType;
import com.example.lawyers.repository.LawTypeRepository;

@Service
public class LawTypeService {
    
    @Autowired
    private LawTypeRepository repo;

    public List<LawType> getLawTypeList()
    {
        return repo.findAll();
    }

    public LawType getLawTypeById(int id)
    {
        return repo.findById(id).get();
    }
    
    public void saveLawType(LawType lawType)
    {
        repo.save(lawType);
    }
}
