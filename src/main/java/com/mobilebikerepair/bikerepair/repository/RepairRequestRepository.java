package com.mobilebikerepair.bikerepair.repository;

import com.mobilebikerepair.bikerepair.model.RepairRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {
    List<RepairRequest> findByTechnicianId(Long technicianId);
    List<RepairRequest> findByCustomerId(Long customerId);
}

