package com.musalasoft.server.repo;


import com.musalasoft.server.model.BatteryLog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryLogRepo extends JpaRepository<BatteryLog, Long>{
}
