package com.example.demo.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private int rating;
    private String comment;
    private String appointmentName;
    private Integer appointmentId;
}
