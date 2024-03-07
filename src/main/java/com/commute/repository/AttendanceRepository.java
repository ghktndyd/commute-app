package com.commute.repository;

import com.commute.domain.Attendance;
import com.commute.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeAndStartWorkBetween(Employee employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    boolean existsByEmployeeAndStartWorkBetween(Employee employee, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId " +
            "AND a.startWork BETWEEN :start AND :end")
    List<Attendance> findAttendancesForEmployeeInMonth(@Param("employeeId") Long employeeId,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);

}
