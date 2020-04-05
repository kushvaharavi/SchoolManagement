package com.demo.schoolmanagement.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.schoolmanagement.exception.ResourceNotFoundException;
import com.demo.schoolmanagement.model.Student;
import com.demo.schoolmanagement.repository.StudentRepository;


@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @GetMapping("/students/{rollNo}")
    public ResponseEntity<Student> getStudentByRollNo(
    @PathVariable(value = "rollNo") Long rollNo) throws ResourceNotFoundException {
    	Student student = studentRepository.findById(rollNo)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found on :: "+ rollNo));
        return ResponseEntity.ok().body(student);
    }
    
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }
    
    @PutMapping("/students/{rollNo}")
    public ResponseEntity<Student> updateStudent(
    @PathVariable(value = "rollNo") Long rollNo,
    @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
    	Student student = studentRepository.findById(rollNo)
          .orElseThrow(() -> new ResourceNotFoundException("Student not found on :: "+ rollNo));
  
    	student.setEmailId(studentDetails.getEmailId());
    	student.setLastName(studentDetails.getLastName());
    	student.setFirstName(studentDetails.getFirstName());
    	student.setUpdatedAt(new Date());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
   }

}
