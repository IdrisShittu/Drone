package com.musalasoft.server.repo;

import com.musalasoft.server.enumeration.DroneState;
import com.musalasoft.server.model.Drone;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepo extends JpaRepository<Drone, Long>{
    Optional<Drone> findBySerialNumber(String serialNumber);
    List<Drone> findByState(DroneState droneState);
    void deleteBySerialNumber(String serialNumber);
    
}
