package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.CustomerDTO;
import com.mobilebikerepair.bikerepair.exception.NotFoundException;
import com.mobilebikerepair.bikerepair.mapper.CustomerMapper;
import com.mobilebikerepair.bikerepair.model.Customer;
import com.mobilebikerepair.bikerepair.repository.CustomerRepository;
import com.mobilebikerepair.bikerepair.repository.RepairRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RepairRequestRepository repairRequestRepository; // <-- added

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerMapper.toDto(
                customerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Customer not found"))
        );
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer customer = customerMapper.toEntity(dto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        return customerMapper.toDto(customerRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer not found");
        }
        // delete children first to satisfy FK constraint
        repairRequestRepository.deleteByCustomerId(id);
        customerRepository.deleteById(id);
    }
}
