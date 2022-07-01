package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.LawSubtype;

@Repository
public interface LawSubtypeRepository extends JpaRepository<LawSubtype, Integer> {
    public LawSubtype findByName(String name);
    public List<LawSubtype> findByLawTypeId(int lawTypeId);
}
