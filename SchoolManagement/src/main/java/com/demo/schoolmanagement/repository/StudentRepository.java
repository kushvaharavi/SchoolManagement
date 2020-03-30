package com.demo.schoolmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.schoolmanagement.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}