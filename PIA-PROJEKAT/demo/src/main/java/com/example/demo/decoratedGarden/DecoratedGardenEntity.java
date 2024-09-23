package com.example.demo.decoratedGarden;

import com.example.demo.decorator.DecoratorEntity;
import com.example.demo.garden.GardenEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "decorated_garden")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecoratedGardenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private GardenEntity garden;

    @ManyToOne
    @JoinColumn(name = "decorator_id", nullable = false)
    private DecoratorEntity decorator;
}
