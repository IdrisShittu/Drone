package com.musalasoft.server.resource;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

import javax.validation.Valid;

import com.musalasoft.server.model.Drone;
import com.musalasoft.server.model.Medication;
import com.musalasoft.server.model.Response;
import com.musalasoft.server.service.Implementation.DroneServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class DroneResource {

    @Autowired
    private DroneServiceImpl droneService;

    
    @PostMapping(value="/drone/register")
    public ResponseEntity<Response> registerDrone(@RequestBody @Valid Drone drone ){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.CREATED)
            .statusCode(HttpStatus.CREATED.value())
            .message("Drone Registeed")
            .data(new HashMap<String, Drone>(){{put("Drone",droneService.register(drone));}})
            .build()
        );
    
    }

    @PutMapping(value="/drone/{serialNumber}")
    public ResponseEntity<?> loadDrone(@PathVariable String serialNumber, @RequestBody @Valid Medication medication ){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.CREATED)
            .statusCode(HttpStatus.CREATED.value())
            .message("Drone Loaded with Medication")
            .data(new HashMap<String, Medication>(){{put("Load",droneService.loadDrone(serialNumber,medication));}})
            .build()
        );
        
    }

    @RequestMapping("/drone/{serialNumber}/load")
    public ResponseEntity<?> checkLoadedItem(@PathVariable String serialNumber){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Drone Loaded Items Fetched")
            .data(new HashMap<String, Collection<Medication>>(){{put("Loaded Items",droneService.checkLoadedItems(serialNumber));}})
            .build()
        );
    }

    @RequestMapping("/drones/available")
    public ResponseEntity<?> checkAvailableDrones(){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Available Drones Fetched")
            .data(new HashMap<String, Collection<Drone>>(){{put("Available Drones",droneService.checkAvailableDrones());}})
            .build()
        );
    }

    @RequestMapping("/drone/{serialNumber}/battery")
    public ResponseEntity<?> checkDroneBatteryLevel(@PathVariable String serialNumber){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Checked Battery Level for Drone: "+ serialNumber)
            .data(new HashMap<String, Integer>(){{put("Battery Level",droneService.checkDroneBatteryLevel(serialNumber));}})
            .build()
        );
    }
    
    @RequestMapping("/drones")
    public ResponseEntity<?> getAllDrones(){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Fetched All Drones")
            .data(new HashMap<String, Collection<Drone>>(){{put("Drones",droneService.getAllDrones());}})
            .build()
        );
    }

    @RequestMapping("/drone/{serialNumber}")
    public ResponseEntity<?> getDrone(@PathVariable String serialNumber){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Fetched Drone with Serial Number" + serialNumber)
            .data(new HashMap<String, Drone>(){{put("Drone",droneService.getBySerialNumber(serialNumber));}})
            .build()
        );

    }

    @RequestMapping("/medications")
    public ResponseEntity<?> getMedications(){
        return ResponseEntity.ok(
            Response.builder()
            .timestamp( LocalDateTime.now())
            .status(HttpStatus.OK)
            .statusCode(HttpStatus.OK.value())
            .message("Fetched Medication")
            .data(new HashMap<String, Collection<Medication>>(){{put("Medications",droneService.getMedications());}})
            .build()
        );
    }

}

