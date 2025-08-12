package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.RepairRequestCreateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestUpdateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestResponseDTO;
import com.mobilebikerepair.bikerepair.model.Customer;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import com.mobilebikerepair.bikerepair.model.RepairType;
import com.mobilebikerepair.bikerepair.model.Technician;
import com.mobilebikerepair.bikerepair.repository.CustomerRepository;
import com.mobilebikerepair.bikerepair.repository.RepairRequestRepository;
import com.mobilebikerepair.bikerepair.repository.RepairTypeRepository;
import com.mobilebikerepair.bikerepair.repository.TechnicianRepository;
import com.mobilebikerepair.bikerepair.service.RepairRequestService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final CustomerRepository customerRepository;
    private final TechnicianRepository technicianRepository;
    private final RepairTypeRepository repairTypeRepository;

    @Override
    public RepairRequestResponseDTO createRequest(RepairRequestCreateDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        RepairType repairType = repairTypeRepository.findById(dto.getRepairTypeId())
                .orElseThrow(() -> new RuntimeException("Repair type not found"));

        RepairRequest request = new RepairRequest();
        request.setDescription(dto.getDescription());
        request.setLocation(dto.getLocation());
        request.setStatus("NEW");
        request.setCustomer(customer);
        request.setRepairType(repairType);

        request = repairRequestRepository.save(request);
        return toDto(request);
    }

    @Override
    public RepairRequestResponseDTO updateRequest(Long id, RepairRequestUpdateDTO dto) {
        RepairRequest request = repairRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repair request not found"));

        if (dto.getStatus() != null) request.setStatus(dto.getStatus());
        if (dto.getTechnicianId() != null) {
            Technician tech = technicianRepository.findById(dto.getTechnicianId())
                    .orElseThrow(() -> new RuntimeException("Technician not found"));
            request.setTechnician(tech);
        }

        request = repairRequestRepository.save(request);
        return toDto(request);
    }

    @Override
    public RepairRequestResponseDTO getById(Long id) {
        return repairRequestRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Repair request not found"));
    }

    @Override
    public List<RepairRequestResponseDTO> getAll() {
        return repairRequestRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<RepairRequestResponseDTO> getByCustomerId(Long customerId) {
        return repairRequestRepository.findByCustomerId(customerId).stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<RepairRequestResponseDTO> getByTechnicianId(Long technicianId) {
        return repairRequestRepository.findByTechnicianId(technicianId).stream()
                .map(this::toDto)
                .toList();
    }

    private RepairRequestResponseDTO toDto(RepairRequest request) {
        RepairRequestResponseDTO dto = new RepairRequestResponseDTO();
        dto.setId(request.getId());
        dto.setDescription(request.getDescription());
        dto.setLocation(request.getLocation());
        dto.setStatus(request.getStatus());
        dto.setCustomerName(request.getCustomer() != null ? request.getCustomer().getName() : null);
        dto.setTechnicianName(request.getTechnician() != null ? request.getTechnician().getName() : null);
        dto.setRepairTypeName(request.getRepairType() != null ? request.getRepairType().getName() : null);
        return dto;
    }
}

