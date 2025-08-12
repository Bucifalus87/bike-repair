package com.mobilebikerepair.bikerepair.repository;

import com.mobilebikerepair.bikerepair.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
