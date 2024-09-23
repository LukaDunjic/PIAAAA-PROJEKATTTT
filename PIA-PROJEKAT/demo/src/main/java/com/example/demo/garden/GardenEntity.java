package com.example.demo.garden;

import com.example.demo.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "garden")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GardenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private UserEntity owner;  // Povezivanje sa vlasnikom (korisnikom)

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GardenType type;  // Tip bašte (PRIVATNA ili RESTORAN)

    @Column(name = "total_area", nullable = false)
    private BigDecimal totalArea;  // Ukupna kvadratura bašte

    @Column(name = "pool_area")
    private BigDecimal poolArea;  // Kvadratura pod bazenom (samo za privatne bašte)

    @Column(name = "greenery_area", nullable = false)
    private BigDecimal greeneryArea;  // Kvadratura pod zelenilom

    @Column(name = "furniture_area")
    private BigDecimal furnitureArea;  // Kvadratura za ležaljke i stolove (ili za restorane - stolovi i stolice)

    @Column(name = "fountain_area")
    private BigDecimal fountainArea;  // Kvadratura pod fontanom (samo za bašte restorana)
}
