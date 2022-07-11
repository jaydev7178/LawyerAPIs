package com.example.lawyers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lawyers.model.Lawyer;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Integer>{
    public List<Lawyer> findById(int id);
    public Lawyer findByEmail(String email);
    public List<Lawyer> findByIdAndDeleted(int id, boolean deleted);

    //@Modifying
    // @Query("update lawyer l set l.name = ?1 ,l.email= ?2 , l.mobile= ?3, l.experience= ?4, l.dob= ?5, l.image= ?6, l.address= ?7, l.city.id= ?8, l.gender= ?9 WHERE l.id = ?10 ")
    // int updateProfile(String name, String email, String mobile, String experience, String dob, String image, String address, String cityId, String gender, int id);
    @Modifying
    @Query("update lawyer  set name = :name ,email= :email, mobile= :mobile, experience= :experience, dob= :dob, image= :image, address= :address, city_id= :city_id, gender= :gender WHERE id = :id ")
    int updateProfile(String name, String email, String mobile, String experience, String dob, String image, String address, int city_id, String gender, int id);


}
