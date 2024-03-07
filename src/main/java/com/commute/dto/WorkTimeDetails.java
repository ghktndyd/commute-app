package com.commute.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WorkTimeDetails {
    private List<WorkDayDetail> details;
    private long sum;

    public WorkTimeDetails(List<WorkDayDetail> details, long sum) {
        this.details = details;
        this.sum = sum;
    }
}
