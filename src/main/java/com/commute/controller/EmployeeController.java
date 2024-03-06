package com.commute.controller;


import com.commute.domain.Employee;
import com.commute.dto.EmployeeRegistrationRequest;
import com.commute.dto.EmployeeInfoResponse;
import com.commute.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeRegistrationRequest request) {
        Employee employee = employeeService.registerEmployee(request);

        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeInfoResponse>> getAllEmployees() {
        List<EmployeeInfoResponse> employeesInfo = employeeService.getAllEmployeesInfo();
        return ResponseEntity.ok(employeesInfo);
    }
}
