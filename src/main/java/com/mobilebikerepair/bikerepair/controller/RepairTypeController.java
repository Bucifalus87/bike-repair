package com.mobilebikerepair.bikerepair.controller;

import com.mobilebikerepair.bikerepair.dto.RepairTypeRequestDTO;
import com.mobilebikerepair.bikerepair.dto.RepairTypeResponseDTO;
import com.mobilebikerepair.bikerepair.service.RepairTypeService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/api/repair-types")
@RequiredArgsConstructor
public class RepairTypeController {

    private final RepairTypeService repairTypeService;


    @PostMapping
    public ResponseEntity<RepairTypeResponseDTO> createRepairType(@RequestBody RepairTypeRequestDTO dto) {
        return new ResponseEntity<>(repairTypeService.createRepairType(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RepairTypeResponseDTO>> getAllRepairTypes() {
        return ResponseEntity.ok(repairTypeService.getAllRepairTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairTypeResponseDTO> getRepairTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(repairTypeService.getRepairTypeById(id));
    }
}

