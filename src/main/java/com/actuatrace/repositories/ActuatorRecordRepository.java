package com.actuatrace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.actuatrace.entities.ActuatorRecord;

public interface ActuatorRecordRepository extends JpaRepository<ActuatorRecord, Long> {
}
