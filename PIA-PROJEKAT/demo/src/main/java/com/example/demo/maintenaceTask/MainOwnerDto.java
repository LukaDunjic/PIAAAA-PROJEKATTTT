package com.example.demo.maintenaceTask;

import com.example.demo.appointment.AppointmentEntity;
import lombok.Data;

import java.util.List;

@Data
public class MainOwnerDto {
    List<MainOwnerMainDto> mainDtos;
    List<MaintenanceTask> maintenanceTaskList;
}
