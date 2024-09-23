package com.example.demo.unregistared;

import com.example.demo.decorator.DecoratorEntity;
import com.example.demo.firm.model.FirmEntity;
import lombok.Data;

import java.util.List;

@Data
public class FirmWithDecoratorsDTO {
    private FirmEntity firmEntity;
    private List<DecoratorEntity> decorators;
}
