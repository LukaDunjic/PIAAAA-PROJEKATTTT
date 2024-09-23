package com.example.demo.appointment;

import com.example.demo.comment.CommentEntity;
import com.example.demo.firm.model.FirmEntity;
import com.example.demo.garden.GardenEntity;
import com.example.demo.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private UserEntity owner;  // Vlasnik bašte

    @ManyToOne
    @JoinColumn(name = "firm_id", nullable = false)
    private FirmEntity firm;  // Firma sa kojom se zakazuje

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private GardenEntity garden;  // Bašta koja se uređuje

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;  // Datum i vreme zakazivanja

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(name = "finished_date")
    private LocalDateTime finishedDate;

    @Column(name = "description", length = 255)
    private String description;  // Dodatni opis

    @Column(name = "reject_comment", length = 255)
    private String rejectComment;

    @Column(name = "num_pools", length = 255)
    private Integer numPools;

    @Column(name = "num_fountains", length = 255)
    private Integer numFountains;
    @Lob
    @Column(name = "picture")
    private byte[] picture;

}
