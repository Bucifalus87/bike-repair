package com.mobilebikerepair.bikerepair.repository;

import com.mobilebikerepair.bikerepair.model.RepairRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {
    List<RepairRequest> findByCustomerId(Long customerId);
    void deleteByCustomerId(Long customerId);
}
