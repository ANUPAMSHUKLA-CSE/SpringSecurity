package com.AnupamSecurity.demo.Service;

import com.AnupamSecurity.demo.DO.Request.CreateRequest;
import com.AnupamSecurity.demo.DO.Response.Reciept;
import com.AnupamSecurity.demo.Mapper.VehicleMapper;
import com.AnupamSecurity.demo.Entities.Vehicle;
import com.AnupamSecurity.demo.Repository.VehicleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleEntityRepoImpl {
    private final VehicleEntityRepository vehicleEntityRepository;
    private final VehicleMapper vehicleMapper;

    public Reciept generate(CreateRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .id(UUID.randomUUID())
                .vehicleName(request.vehicleName())
                .vehicleNumber(request.vehicleNumber())
                .amountCharged(request.amountCharged())
                .capacity(request.capacity())
                .build();
        vehicleEntityRepository.save(vehicle);
       return  vehicleMapper.toReciept(vehicle);
    }

    public List<Reciept> allVehiclePersonDetails() {
        List<Vehicle> vehicles = vehicleEntityRepository.findAll();
        return vehicleMapper.toList(vehicles);
    }

    public Reciept getName(String name) {
         Vehicle vehicle=vehicleEntityRepository.getByVehicleName(name);
         return vehicleMapper.toReciept(vehicle);
    }

    public void  deleteAll() {
        vehicleEntityRepository.deleteAll();


    }
}
