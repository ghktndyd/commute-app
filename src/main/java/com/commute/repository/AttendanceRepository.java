package com.commute.repository;

import com.commute.domain.Attendance;
import com.commute.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeAndStartWorkBetween(Employee employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    boolean existsByEmployeeAndStartWorkBetween(Employee employee, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
