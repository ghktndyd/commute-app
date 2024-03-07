package com.commute.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class WorkDayDetail {

    private LocalDate date;
    private long workingMinutes;

    public WorkDayDetail(LocalDate date, long workingMinutes) {
        this.date = date;
        this.workingMinutes = workingMinutes;
    }
}
