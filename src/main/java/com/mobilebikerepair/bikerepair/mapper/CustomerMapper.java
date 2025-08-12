package com.mobilebikerepair.bikerepair.mapper;

import com.mobilebikerepair.bikerepair.dto.CustomerDTO;
import com.mobilebikerepair.bikerepair.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDto(Customer entity) {
        if (entity == null) {
            return null;
        }
        return new CustomerDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }

    public Customer toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
