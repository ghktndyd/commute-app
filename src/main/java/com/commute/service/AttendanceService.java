package com.commute.service;

import com.commute.domain.Attendance;
import com.commute.domain.Employee;
import com.commute.dto.WorkDayDetail;
import com.commute.dto.WorkTimeDetails;
import com.commute.repository.AttendanceRepository;
import com.commute.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    private final LocalDateTime NOW = LocalDateTime.now();
    private final LocalDateTime START_OF_DAY = LocalDate.now().atStartOfDay();
    private final LocalDateTime END_OF_DAY = LocalDate.now().atTime(23, 59, 59);

    @Transactional
    public String startWork(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        checkAlreadyAttendance(employee);
        Attendance attendance = Attendance.recordAttendance(employee, NOW);
        attendanceRepository.save(attendance);

        return "출근 처리 되었습니다.";
    }

    @Transactional
    public String endWork(Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        Attendance attendance = checkAttendance(employee);
        attendance.recordEndWork(NOW);
        attendanceRepository.save(attendance);

        return "퇴근 처리 되었습니다.";
    }

    @Transactional(readOnly = true)
    public WorkTimeDetails getMonthlyWorkTime(Long employeeId, String yearMonthStr) {
        YearMonth yearMonth = YearMonth.parse(yearMonthStr);
        LocalDateTime start = getStartOfMonth(yearMonth);
        LocalDateTime end = getEndOfMonth(yearMonth);

        List<Attendance> attendances = attendanceRepository.findAttendancesForEmployeeInMonth(employeeId, start, end);

        List<WorkDayDetail> workDayDetails = calculateWorkDayDetails(attendances);
        long sum = calculateTotalWorkMinutes(workDayDetails);

        return new WorkTimeDetails(workDayDetails, sum);
    }

    private Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 id입니다"));
    }

    private void checkAlreadyAttendance(Employee employee) {
        boolean alreadyStarted = attendanceRepository.existsByEmployeeAndStartWorkBetween(employee, START_OF_DAY, END_OF_DAY);
        if (alreadyStarted) {
            throw new IllegalStateException("이미 출근 처리 되었습니다.");
        }
    }

    private Attendance checkAttendance(Employee employee) {
        Attendance attendance = attendanceRepository.findByEmployeeAndStartWorkBetween(employee, START_OF_DAY, END_OF_DAY)
                .orElseThrow(() -> new IllegalStateException("출근 처리 되지 않았습니다."));

        if (attendance.getEndWork() != null) {
            throw new IllegalStateException("이미 퇴근 처리 되었습니다.");
        }

        return attendance;
    }

    private LocalDateTime getStartOfMonth(YearMonth yearMonth) {
        return yearMonth.atDay(1).atStartOfDay();
    }

    private LocalDateTime getEndOfMonth(YearMonth yearMonth) {
        return yearMonth.atEndOfMonth().atTime(LocalTime.MAX);
    }

    private List<WorkDayDetail> calculateWorkDayDetails(List<Attendance> attendances) {
        return attendances.stream()
                .filter(attendance -> attendance.getEndWork() != null)
                .map(attendance -> new WorkDayDetail(attendance.getStartWork().toLocalDate(),
                        Duration.between(attendance.getStartWork(), attendance.getEndWork()).toMinutes()))
                .collect(Collectors.toList());
    }

    private long calculateTotalWorkMinutes(List<WorkDayDetail> workDayDetails) {
        return workDayDetails.stream().mapToLong(WorkDayDetail::getWorkingMinutes).sum();
    }

}
