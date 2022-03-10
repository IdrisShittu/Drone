package com.musalasoft.server;

import com.musalasoft.server.repo.*;
import com.musalasoft.server.enumeration.DroneModel;
import com.musalasoft.server.model.*;
import com.musalasoft.server.enumeration.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	@Autowired
	private DroneRepo droneRepo;

	@Autowired
	private MedicationRepo medicationRepo;

	@Autowired
	private BatteryLogRepo batteryLogRepo;


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		



		Drone a =   Drone.builder()
					.serialNumber("00001")
					.battery(50)
					.model(DroneModel.MIDDLEWEIGHT)
					.weight(500)
					.state(DroneState.IDLE)
					.build();

					Drone b =	Drone.builder()
					.serialNumber("00002")
					.battery(30)
					.model(DroneModel.MIDDLEWEIGHT)
					.weight(500)
					.state(DroneState.IDLE)
					.build();

					Drone c =  Drone.builder()
					.serialNumber("00003")
					.battery(40)
					.model(DroneModel.HEAVYWEIGHT)
					.weight(500)
					.state(DroneState.IDLE)
					.build();

					Drone dr =  Drone.builder()
					.serialNumber("0004")
					.battery(40)
					.model(DroneModel.HEAVYWEIGHT)
					.weight(500)
					.state(DroneState.IDLE)
					.build();

					droneRepo.save(a);
					droneRepo.save(b);
					droneRepo.save(c);
					droneRepo.save(dr);

					BatteryLog f = BatteryLog.builder()
					.battery(25)
					.serialNumber("00002")
                    .build();

					batteryLogRepo.save(f);

			

					Medication d = Medication.builder()
					.name("PARACETAMOL")
					.code("001")
					.weight(250)
					.imageURI("https://bit.ly.com/dbdbfb")
					.build();

					a.setState(DroneState.LOADING);
					droneRepo.save(a);
				

				//	d.setDrone(a);

					medicationRepo.save(d);
					

					Medication e = Medication.builder()
					.name("Penicilin")
					.code("002")
					.weight(200)
					.imageURI("https://bit.ly.com/dgdgdgfb")
					.build();

					medicationRepo.save(e); 

					

	}


}
