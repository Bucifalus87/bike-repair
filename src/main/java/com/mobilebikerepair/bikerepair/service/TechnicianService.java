package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.TechnicianRequestDTO;
import com.mobilebikerepair.bikerepair.dto.TechnicianResponseDTO;

import java.util.List;


public interface TechnicianService {
    TechnicianResponseDTO createTechnician(TechnicianRequestDTO dto);
    TechnicianResponseDTO getTechnicianById(Long id);
    List<TechnicianResponseDTO> getAllTechnicians();
}
