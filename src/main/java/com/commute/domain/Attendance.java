package com.commute.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "uni_attendance_1", columnNames = {"employeeId"})
)
public class Attendance {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Setter
    private LocalDateTime startWork;

    @Setter
    private LocalDateTime endWork;

    public static Attendance recordAttendance(Employee employee, LocalDateTime startWork) {
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setStartWork(startWork);
        return attendance;
    }

    public void recordEndWork(LocalDateTime now) {
        this.setEndWork(now);
    }
}
