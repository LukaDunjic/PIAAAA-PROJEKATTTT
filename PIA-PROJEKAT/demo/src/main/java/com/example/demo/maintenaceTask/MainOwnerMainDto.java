package com.example.demo.maintenaceTask;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainOwnerMainDto {
    private Integer appointmentId;
    private LocalDateTime finishedServiceTime;
    private LocalDateTime finishedTime;
    private Integer numPools;
    private Integer numFountains;
}
