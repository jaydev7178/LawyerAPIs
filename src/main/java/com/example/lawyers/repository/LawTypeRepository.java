package com.example.lawyers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.LawType;

@Repository
public interface LawTypeRepository  extends JpaRepository<LawType, Integer>{
    public LawType findByName(String name);
}
