package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.Lawyer;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Integer>{
    public List<Lawyer> findById(int id);
    public Lawyer findByEmail(String email);
    public List<Lawyer> findByIdAndDeleted(int id, boolean deleted);
}
