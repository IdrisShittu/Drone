package com.musalasoft.server.service;

import java.util.Collection;

import com.musalasoft.server.model.Drone;
import com.musalasoft.server.model.Medication;
 

public interface DroneService {
    Drone register(Drone drone);
    Medication loadDrone(String serialNumber, Medication medication);
    Collection<Medication> checkLoadedItems(String serialNumber);
    Collection<Drone> checkAvailableDrones();
    Integer checkDroneBatteryLevel(String serialNUmber);

  
   
   
}
