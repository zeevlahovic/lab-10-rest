package com.zee.controller;

import com.zee.dto.ResponseWrapper;
import com.zee.dto.StudentDTO;
import com.zee.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

     /*
           Endpoint: /api/v1/student
           HTTP Status Code: 200

           JSON Response Body:
           "success": true
           "message": "Students are successfully retrieved."
           "code":200
           "data":<students data>
     */
    @GetMapping()
    public ResponseEntity<ResponseWrapper> readAllStudents() {

        return ResponseEntity.ok(ResponseWrapper.builder()
                .success(true)
                .message("Students are successfully retrieved.")
                .code(HttpStatus.OK.value())
                .data(studentService.findAll())
                .build());
    }

    /*

          Endpoint: /api/v1/student
          HTTP Status Code: 201

          JSON Response Body:
          "success": true
          "message": "Student is successfully created."
          "code":201
          "data":<created student data>
    */
    @PostMapping()
    public ResponseEntity<ResponseWrapper>createStudent(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(ResponseWrapper.builder()
                .success(true)
                .message("Student is successfully created.")
                .code(HttpStatus.CREATED.value())
                .data(studentService.createStudent(studentDTO))
                .build());
    }

}
