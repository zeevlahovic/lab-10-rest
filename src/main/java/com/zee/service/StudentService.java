package com.zee.service;

import com.zee.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> findAll();

    StudentDTO createStudent(StudentDTO studentDTO);

}