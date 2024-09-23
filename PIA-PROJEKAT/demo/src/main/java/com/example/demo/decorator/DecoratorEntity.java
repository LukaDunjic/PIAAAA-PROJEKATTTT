package com.example.demo.decorator;

import com.example.demo.firm.model.FirmEntity;
import com.example.demo.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "decorator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecoratorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    private FirmEntity firm;

}
