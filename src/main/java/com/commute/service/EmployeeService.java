package com.commute.service;

import com.commute.domain.Employee;
import com.commute.domain.Team;
import com.commute.dto.EmployeeRequest;
import com.commute.dto.EmployeeResponse;
import com.commute.repository.EmployeeRepository;
import com.commute.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public Employee registerEmployee(EmployeeRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(IllegalArgumentException::new);

        Employee employee = Employee.builder()
                .name(request.getName())
                .isManager(request.isManager())
                .entryDate(request.getEntryDate())
                .birthDay(request.getBirthDay())
                .team(team)
                .build();

        return employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getAllEmployeesInfo() {
        return employeeRepository.findAllEmployeeInfo();
    }
}
