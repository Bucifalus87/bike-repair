package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.CustomerRequestDTO;
import com.mobilebikerepair.bikerepair.dto.CustomerResponseDTO;
import java.util.List;


public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto);
    void deleteCustomer(Long id);
}

