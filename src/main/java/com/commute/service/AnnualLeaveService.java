package com.commute.service;

import com.commute.domain.Employee;
import com.commute.dto.AnnualLeaveRequest;
import com.commute.repository.AnnualLeaveRepository;
import com.commute.repository.EmployeeRepository;
import com.commute.repository.LeavePolicyRepository;
import com.commute.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class AnnualLeaveService {

    private final EmployeeRepository employeeRepository;
    private final AnnualLeaveRepository annualLeaveRepository;
    private final LeavePolicyRepository leavePolicyRepository;
    private final TeamRepository teamRepository;

    public void useAnnualLeave(AnnualLeaveRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사원을 찾을 수 없습니다."));

        if (employee.getRemainingAnnualLeave() <= 0) {
            throw new IllegalArgumentException("연차가 존재하지 않습니다.");
        }

        if (request.getLeaveDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("과거로는 연차를 사용할 수 없습니다.");
        }
    }
}
