package com.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnnualLeaveRequest {
    private Long employeeId;
    private LocalDate leaveDate;
}
