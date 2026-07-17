package com.AnupamSecurity.demo.Repository;

import com.AnupamSecurity.demo.DO.Response.Reciept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleEntityRepository extends JpaRepository<Vehicle, UUID> {
    Vehicle getByVehicleName(String vehicleName);

}
