package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.RepairRequestDTO;
import java.util.List;

public interface RepairRequestService {
    List<RepairRequestDTO> getAll();
    RepairRequestDTO getById(Long id);
    RepairRequestDTO create(RepairRequestDTO dto);
    RepairRequestDTO update(Long id, RepairRequestDTO dto);
    void delete(Long id);
}
