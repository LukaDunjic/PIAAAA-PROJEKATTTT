package com.example.demo.appointment.dtos;

import lombok.Data;

@Data
public class AppointmentDTO {
    private Integer id;
    private String appointmentDate;
    private String firmName;
    private String comment;
    private Integer rating;

    public AppointmentDTO(Integer id, String appointmentDate, String firmName, String comment, Integer rating) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.firmName = firmName;
        this.comment = comment;
        this.rating = rating;
    }
}