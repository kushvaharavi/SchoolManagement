package com.demo.schoolmanagement.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

   @DeleteMapping("/student/{rollNo}")
   public Map<String, Boolean> deleteStudent(
       @PathVariable(value = "rollNo") Long rollNo) throws Exception {
	   Student student = studentRepository.findById(rollNo)
          .orElseThrow(() -> new ResourceNotFoundException("Student not found on :: "+ rollNo));

	   studentRepository.delete(student);
       Map<String, Boolean> response = new HashMap<>();
       response.put("deleted", Boolean.TRUE);
       return response;
   }
}
