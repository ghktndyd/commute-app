package com.commute.service;

import com.commute.domain.Employee;
import com.commute.domain.Team;
import com.commute.dto.EmployeeRegistrationRequest;
import com.commute.dto.EmployeeInfoResponse;
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

    public Employee registerEmployee(EmployeeRegistrationRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 아이디입니다."));

        Employee employee = Employee.builder()
                .name(request.getName())
                .role(request.getRole())
                .entryDate(request.getEntryDate())
                .birthDay(request.getBirthDay())
                .team(team)
                .build();

        return employeeRepository.save(employee);
    }

    public List<EmployeeInfoResponse> getAllEmployeesInfo() {
        return employeeRepository.findAllEmployeeInfo();
    }
}
