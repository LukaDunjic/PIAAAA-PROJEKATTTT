package com.example.demo.unregistared;

import lombok.Data;

import java.util.List;

@Data
public class MainPageDTO {
    private Integer totalDecoratedGardens;
    private Integer totalOwners;
    private Integer totalDecorators;
    private Integer appointmentsLast24Hours;
    private Integer appointmentsLast7Days;
    private Integer appointmentsLast30Days;
    private List<FirmWithDecoratorsDTO> firms;
    private List<String> gallery;
}
