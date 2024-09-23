package com.example.demo.firm.model;

import com.example.demo.service.ServiceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "firm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FirmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long firmId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating;

    @Column(name = "website_link", length = 150)
    private String websiteLink;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

//
//    @OneToMany(mappedBy = "firm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    //private List<ServiceEntity> services; // Lista usluga koje firma nudi
}
