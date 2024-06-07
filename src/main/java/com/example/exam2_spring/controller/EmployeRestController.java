package com.example.exam2_spring.controller;

import com.example.exam2_spring.entities.Employe;
import com.example.exam2_spring.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeRestController {

    @Autowired
    private EmployeRepository employeRepository;

    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('DEV', 'TEST', 'DEVOPS')")
    public ResponseEntity<List<Employe>> getAllEmployees() {
        List<Employe> employees = employeRepository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
