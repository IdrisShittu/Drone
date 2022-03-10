package com.musalasoft.server.repo;

import java.util.List;

import com.musalasoft.server.model.Medication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepo extends JpaRepository<Medication, Long> {
    List<Medication> findByDroneSerialNumber(String serialNumber);
}
