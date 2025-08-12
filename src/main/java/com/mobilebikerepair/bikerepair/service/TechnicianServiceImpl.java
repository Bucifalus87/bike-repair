package com.mobilebikerepair.bikerepair.service;


import com.mobilebikerepair.bikerepair.dto.TechnicianRequestDTO;
import com.mobilebikerepair.bikerepair.dto.TechnicianResponseDTO;
import com.mobilebikerepair.bikerepair.model.Role;
import com.mobilebikerepair.bikerepair.model.Technician;
import com.mobilebikerepair.bikerepair.model.User;
import com.mobilebikerepair.bikerepair.repository.TechnicianRepository;
import com.mobilebikerepair.bikerepair.repository.UserRepository;
import com.mobilebikerepair.bikerepair.service.TechnicianService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianServiceImpl implements TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TechnicianResponseDTO createTechnician(TechnicianRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.TECHNICIAN);
        user = userRepository.save(user);

        Technician technician = new Technician();
        technician.setName(dto.getName());
        technician.setUser(user);
        technician = technicianRepository.save(technician);

        return toDto(technician);
    }

    @Override
    public TechnicianResponseDTO getTechnicianById(Long id) {
        return technicianRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Technician not found"));
    }

    @Override
    public List<TechnicianResponseDTO> getAllTechnicians() {
        return technicianRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private TechnicianResponseDTO toDto(Technician technician) {
        TechnicianResponseDTO dto = new TechnicianResponseDTO();
        dto.setId(technician.getId());
        dto.setName(technician.getName());
        return dto;
    }
}
