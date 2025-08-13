package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.RepairRequestDTO;
import com.mobilebikerepair.bikerepair.exception.NotFoundException;
import com.mobilebikerepair.bikerepair.kafka.RepairEventProducer;
import com.mobilebikerepair.bikerepair.mapper.RepairRequestMapper;
import com.mobilebikerepair.bikerepair.model.Customer;
import com.mobilebikerepair.bikerepair.model.RepairRequest;
import com.mobilebikerepair.bikerepair.repository.CustomerRepository;
import com.mobilebikerepair.bikerepair.repository.RepairRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RepairRequestServiceImpl implements RepairRequestService {

    private final RepairRequestRepository repairRequestRepository;
    private final CustomerRepository customerRepository;
    private final RepairRequestMapper repairRequestMapper;
    private final RepairEventProducer repairEventProducer;

    @Override
    public List<RepairRequestDTO> getAll() {
        return repairRequestRepository.findAll()
                .stream()
                .map(repairRequestMapper::toDto)
                .toList();
    }

    @Override
    public RepairRequestDTO getById(Long id) {
        return repairRequestMapper.toDto(
                repairRequestRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Repair request not found"))
        );
    }

    @Override
    public RepairRequestDTO create(RepairRequestDTO dto) {
        // Ensure customer exists
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        // Map and set relations
        RepairRequest entity = repairRequestMapper.toEntity(dto);
        entity.setCustomer(customer);

        // Persist
        RepairRequest saved = repairRequestRepository.save(entity);

        // Publish Kafka event (non-blocking)
        repairEventProducer.sendRepairCreated(saved);

        // Return DTO
        return repairRequestMapper.toDto(saved);
    }

    @Override
    public RepairRequestDTO update(Long id, RepairRequestDTO dto) {
        RepairRequest existing = repairRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Repair request not found"));

        existing.setDescription(dto.getDescription());
        existing.setState(dto.getState());

        return repairRequestMapper.toDto(repairRequestRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!repairRequestRepository.existsById(id)) {
            throw new NotFoundException("Repair request not found");
        }
        repairRequestRepository.deleteById(id);
    }
}
