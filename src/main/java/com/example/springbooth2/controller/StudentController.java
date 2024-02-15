package com.example.springbooth2.controller;

import com.example.springbooth2.model.Student;
import com.example.springbooth2.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/all")
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PostMapping("/new")
    private ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            studentService.saveOrUpdate(student);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("New Student created with id: " + student.getId(), HttpStatus.CREATED);
    }




    @GetMapping("/ageGreaterThan/{age}")
    private List<Student> getStudentsByAgeGreaterThan(@PathVariable Integer age) {
        return studentService.getStudentsByAgeGreaterThan(age);
    }


    @GetMapping("/nameContaining/{name}")
    private List<Student> getStudentsByNameContaining(@PathVariable String name) {
        return studentService.getStudentsByNameContaining(name);
    }


    @DeleteMapping("/deleteByName/{name}")
    private ResponseEntity<String> deleteStudentByName(@PathVariable String name) {
        studentService.deleteStudentByName(name);
        return new ResponseEntity<>("Student with name " + name + " deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/countByAgeGreaterThan/{age}")
    private long countStudentsByAgeGreaterThan(@PathVariable Integer age) {
        return studentService.countStudentsByAgeGreaterThan(age);
    }

    @PutMapping("/updateAge/{id}/{age}")

    private ResponseEntity<String> updateStudentAge( @PathVariable Integer id, @PathVariable Integer age) {
        try {
            Student student = studentService.getStudentById(id);
            if (student != null) {
                student.setAge(age);
                studentService.saveOrUpdate(student);
                return new ResponseEntity<>("Student with ID " + id + " age updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}