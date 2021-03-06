package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{
    public List<State> findByCountryId(int countryId);
}
