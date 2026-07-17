package com.AnupamSecurity.demo.Mapper;


import com.AnupamSecurity.demo.DO.Response.Reciept;
import com.AnupamSecurity.demo.Entities.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Reciept toReciept(Vehicle vehicle);   //source is vehicle and reciept is target

    List<Reciept> toList(List<Vehicle> vehicles);
}
