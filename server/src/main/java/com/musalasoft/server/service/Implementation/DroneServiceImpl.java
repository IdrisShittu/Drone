package com.musalasoft.server.service.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.Optional;

import com.musalasoft.server.enumeration.*;
import com.musalasoft.server.model.BatteryLog;
import com.musalasoft.server.model.Drone;
import com.musalasoft.server.model.Medication;
import com.musalasoft.server.repo.BatteryLogRepo;
import com.musalasoft.server.repo.DroneRepo;
import com.musalasoft.server.repo.MedicationRepo;
import com.musalasoft.server.service.DroneService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {
   
    @Autowired
    private DroneRepo droneRepo;
    @Autowired
    private MedicationRepo medicationRepo;
    @Autowired
    private BatteryLogRepo batteryLogRepo;


    @Override
    public Drone register(Drone drone){

        if(drone.getBattery()<0 || drone.getBattery()>100)throw new IllegalStateException("Invalid Battery Capacity");

        if(drone.getWeight()<0 || drone.getWeight()>500) throw new IllegalStateException("Invalid Weight Limit");

        if(drone.getSerialNumber().length()>99)throw new IllegalStateException("Invalid Serial Number Length");

		if(drone.getState().equals(DroneState.LOADING))throw new IllegalStateException("Invalid Drone State");
		

        return this.droneRepo.save(drone);
    };

    
    @Override
    public Medication loadDrone(String serialNumber, Medication medication){

        if(checkMedicationCode(medication.getCode()))new IllegalArgumentException("Invalid Medication Code");
        if(checkMedicationName(medication.getName()))new IllegalArgumentException("Invalid Med Code"); 
        
        Drone drone = this.droneRepo.findBySerialNumber(serialNumber).orElseThrow(() ->
				new IllegalArgumentException("Drone with Serial Number "+serialNumber+" not found"));
       
        if(drone.getBattery()<25)throw new IllegalStateException("Battery low");
        
        if(!drone.getState().equals(DroneState.IDLE) && !drone.getState().equals(DroneState.LOADING))
            throw new IllegalStateException("Drone is not in a loadeable State");

        Integer totalWeight=0;
        List<Medication> medications=new ArrayList<Medication>();;

     
        if(!(droneRepo.findBySerialNumber(serialNumber).get().getMedications().size()==0)){
        medications = droneRepo.findBySerialNumber(serialNumber).get().getMedications();
        totalWeight = medications.stream().map(Medication::getWeight).reduce(0, Integer::sum);
        
    }
        
        totalWeight += medication.getWeight();
        
        if(totalWeight > drone.getWeight()){
            throw new IllegalArgumentException("Item weight exceed droan Max weight");
        
        }

        if(drone.getState().equals(DroneState.IDLE)){
            drone.setState(DroneState.LOADING);
            droneRepo.save(drone);

        }

        medication.setDrone(drone);  

        return medicationRepo.save(medication);
   
    }; 

    @Override
    public Collection<Medication> checkLoadedItems(String serialNumber){
        return medicationRepo.findByDroneSerialNumber(serialNumber);

    }

    @Override
    public Collection<Drone> checkAvailableDrones(){
        return this.droneRepo.findByState(DroneState.IDLE);
    };

    @Override
    public Integer checkDroneBatteryLevel(String serialNumber){
        return droneRepo.findBySerialNumber(serialNumber).get().getBattery();
    
    }

    public Collection<Drone> getAllDrones(){
        return this.droneRepo.findAll();
    }

    public Drone getBySerialNumber(String serialNumber){
        return this.droneRepo.findBySerialNumber(serialNumber).get();
    };

 
    
    public void delete(String serialNumber){
        this.droneRepo.deleteBySerialNumber(serialNumber);
    };
    

    public Collection<Medication> getMedications(){
        return this.medicationRepo.findAll();
    }

    
    @Scheduled(fixedDelay = 20)
    public void takeBatteryLog(){

        for(Drone d:droneRepo.findAll()){
            BatteryLog logger = new BatteryLog();
            logger.setSerialNumber(d.getSerialNumber());
            logger.setBattery(d.getBattery());
            batteryLogRepo.save(logger);
        }
    }

    public static boolean checkMedicationName(String s){
        for(int i = 0; i < s.length(); i++){
            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9')&&
              !(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') &&
              !(s.charAt(i) >= 'a' && s.charAt(i) <= 'z') &&
              s.charAt(i) != '-' && s.charAt(i) != '_'){
                return false;
            }
        }
        return true;
    }

    public static boolean checkMedicationCode(String s){
        for(int i = 0; i < s.length(); i++){
            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9') &&
              !(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') &&
              s.charAt(i) != '_'){
                return false;
            }
        }
        return true;
    }


}
