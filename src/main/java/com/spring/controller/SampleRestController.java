package com.spring.controller;

import com.spring.domain.Employee;
import com.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SampleRestController {


    @Autowired
    EmployeeRepository employeeRepository;


    @GetMapping(value = "/employees", produces = "application/json")
    @ResponseBody
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }


}
