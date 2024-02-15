package com.example.springbooth2.repository;


import com.example.springbooth2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer > {
   List<Student> findByAgeGreaterThan(Integer age);
    List<Student> findByNameContaining(String name);
    void deleteByName(String name);
    long countByAgeGreaterThan(Integer age);
}


