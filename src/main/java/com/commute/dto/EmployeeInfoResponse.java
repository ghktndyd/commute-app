package com.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoResponse {
    private String name;
    private String teamName;
    private String role;
    private LocalDate birthDay;
    private LocalDate entryDate;
    private int remainingAnnualLeave;
}
