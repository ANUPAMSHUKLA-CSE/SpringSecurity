package com.AnupamSecurity.demo.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private  String vehicleName;
    @NotNull(message="vehichle no. is required")
    private Integer vehicleNumber;
    private  Integer capacity;
    @NotNull(message = "Amount is required i.e. hpw much is the parking charge")
    private Integer amountCharged;

}
