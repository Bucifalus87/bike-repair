package com.mobilebikerepair.bikerepair.service;

import com.mobilebikerepair.bikerepair.dto.CustomerRequestDTO;
import com.mobilebikerepair.bikerepair.dto.CustomerResponseDTO;
import com.mobilebikerepair.bikerepair.model.Customer;
import com.mobilebikerepair.bikerepair.model.Role;
import com.mobilebikerepair.bikerepair.model.User;
import com.mobilebikerepair.bikerepair.repository.CustomerRepository;
import com.mobilebikerepair.bikerepair.repository.UserRepository;
import com.mobilebikerepair.bikerepair.service.CustomerService;
import com.mobilebikerepair.bikerepair.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CUSTOMER);
        user = userRepository.save(user);

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setUser(user);
        customer = customerRepository.save(customer);

        return toDto(customer);
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        if (dto.getName() != null)  c.setName(dto.getName());
        if (dto.getEmail() != null) c.setEmail(dto.getEmail());
        // phone if you store it on Customer:
        // if (dto.getPhone() != null) c.setPhone(dto.getPhone());
        return toDto(c);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) throw new NotFoundException("Customer not found");
        customerRepository.deleteById(id);
    }

    private CustomerResponseDTO toDto(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        return dto;
    }
}

