package com.example.demo.appointment.dtos;

import lombok.Data;

@Data
public class JobsPerMonthDto {
    private int month;
    private long jobCount;

    public JobsPerMonthDto() {}

    public JobsPerMonthDto(int month, long jobCount) {
        this.month = month;
        this.jobCount = jobCount;
    }
}
