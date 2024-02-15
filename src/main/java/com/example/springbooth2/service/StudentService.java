package com.example.springbooth2.service;
import com.example.springbooth2.model.Student;
import com.example.springbooth2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudentsByAgeGreaterThan(Integer age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student> getStudentsByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    @Transactional
    public void deleteStudentByName(String name) {
        studentRepository.deleteByName(name);
    }

//    When the deleteByName method was called on the studentRepository, it attempted to execute the delete operation directly on the database without a transaction. However, JPA requires an active transaction to perform modifications to the database.

    public long countStudentsByAgeGreaterThan(Integer age) {
        return studentRepository.countByAgeGreaterThan(age);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
