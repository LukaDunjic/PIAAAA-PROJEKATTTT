package com.example.demo.appointment.dtos;

import lombok.Data;

@Data
public class AvgJobsPerDayOfWeekDto {
    private int dayOfWeek;
    private double avgJobCount;

    public AvgJobsPerDayOfWeekDto() {}

    public AvgJobsPerDayOfWeekDto(int dayOfWeek, double avgJobCount) {
        this.dayOfWeek = dayOfWeek;
        this.avgJobCount = avgJobCount;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getAvgJobCount() {
        return avgJobCount;
    }

    public void setAvgJobCount(double avgJobCount) {
        this.avgJobCount = avgJobCount;
    }
}
