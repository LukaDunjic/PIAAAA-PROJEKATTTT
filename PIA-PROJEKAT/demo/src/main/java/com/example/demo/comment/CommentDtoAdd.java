package com.example.demo.comment;

import lombok.Data;

@Data
public class CommentDtoAdd {
    private Integer id;
    private Integer rating;
    private String comment;
    private String firmName;
    private String appointmentDate;
}
