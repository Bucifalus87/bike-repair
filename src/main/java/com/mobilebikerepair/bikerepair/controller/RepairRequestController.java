package com.mobilebikerepair.bikerepair.controller;

import com.mobilebikerepair.bikerepair.dto.RepairRequestCreateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestUpdateDTO;
import com.mobilebikerepair.bikerepair.dto.RepairRequestResponseDTO;
import com.mobilebikerepair.bikerepair.service.RepairRequestService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;



@RestController
@RequestMapping("/api/repair-requests")
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;


    @PostMapping
    public ResponseEntity<RepairRequestResponseDTO> createRepairRequest(@RequestBody RepairRequestCreateDTO dto) {
        return new ResponseEntity<>(repairRequestService.createRequest(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RepairRequestResponseDTO> updateRepairRequest(
            @PathVariable Long id,
            @RequestBody RepairRequestUpdateDTO dto) {
        return ResponseEntity.ok(repairRequestService.updateRequest(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairRequestResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(repairRequestService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RepairRequestResponseDTO>> getAll() {
        return ResponseEntity.ok(repairRequestService.getAll());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<RepairRequestResponseDTO>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(repairRequestService.getByCustomerId(customerId));
    }

    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<List<RepairRequestResponseDTO>> getByTechnician(@PathVariable Long technicianId) {
        return ResponseEntity.ok(repairRequestService.getByTechnicianId(technicianId));
    }
}
