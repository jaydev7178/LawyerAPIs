package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lawyers.model.LawyerLawSubtypeMapping;

public interface LawyerLawSubtypeMappingRepository  extends JpaRepository<LawyerLawSubtypeMapping, Integer>{
    public List<LawyerLawSubtypeMapping> findByLawyerId(int lawyerId);
    public LawyerLawSubtypeMapping findByLawSubtypeId(int lawSubtypeId);

}
