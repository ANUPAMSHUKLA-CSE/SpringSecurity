package com.AnupamSecurity.demo.Controller;


import com.AnupamSecurity.demo.DO.Request.CreateRequest;
import com.AnupamSecurity.demo.DO.Response.Reciept;
import com.AnupamSecurity.demo.Service.VehicleEntityRepoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/")
public class VehicleController {
    private final VehicleEntityRepoImpl service;

    @PostMapping("/entry")
    public ResponseEntity<Reciept> entry(@RequestBody CreateRequest request)
    {
        return ResponseEntity.ok(service.generate(request));
    }

    @GetMapping("/allList")
    public ResponseEntity<List<Reciept>>allList()
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.allVehiclePersonDetails());
    }

    @GetMapping("/Name/{name}")
    public ResponseEntity<Reciept> findByName(@PathVariable String name)
    {
        return  ResponseEntity.ok(service.getName(name));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll() {

        service.deleteAll();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
