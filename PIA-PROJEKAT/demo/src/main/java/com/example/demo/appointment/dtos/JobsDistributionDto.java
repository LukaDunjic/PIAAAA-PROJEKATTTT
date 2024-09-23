package com.example.demo.appointment.dtos;

import lombok.Data;

@Data
public class JobsDistributionDto {
    private Integer decoratorId;
    private long jobCount;

    public JobsDistributionDto() {}

    public JobsDistributionDto(Integer decoratorId, long jobCount) {
        this.decoratorId = decoratorId;
        this.jobCount = jobCount;
    }
}
