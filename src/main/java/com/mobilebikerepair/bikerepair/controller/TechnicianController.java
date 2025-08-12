package com.mobilebikerepair.bikerepair.controller;

import com.mobilebikerepair.bikerepair.dto.TechnicianRequestDTO;
import com.mobilebikerepair.bikerepair.dto.TechnicianResponseDTO;
import com.mobilebikerepair.bikerepair.service.TechnicianService;

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
@RequestMapping("/api/technicians")
@RequiredArgsConstructor
public class TechnicianController {

    private final TechnicianService technicianService;

    @PostMapping
    public ResponseEntity<TechnicianResponseDTO> createTechnician(@RequestBody TechnicianRequestDTO dto) {
        return new ResponseEntity<>(technicianService.createTechnician(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianResponseDTO> getTechnicianById(@PathVariable Long id) {
        return ResponseEntity.ok(technicianService.getTechnicianById(id));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianResponseDTO>> getAllTechnicians() {
        return ResponseEntity.ok(technicianService.getAllTechnicians());
    }
}

