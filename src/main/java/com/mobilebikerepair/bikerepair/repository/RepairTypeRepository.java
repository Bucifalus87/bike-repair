package com.mobilebikerepair.bikerepair.repository;


import com.mobilebikerepair.bikerepair.model.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairTypeRepository extends JpaRepository<RepairType, Long> {
}

