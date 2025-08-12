package com.mobilebikerepair.bikerepair.controller;

import com.mobilebikerepair.bikerepair.dto.RepairRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.mobilebikerepair.bikerepair.service.RepairRequestService;



import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/repair-requests")
@RequiredArgsConstructor
public class RepairRequestController {

    private final RepairRequestService repairRequestService;

    @GetMapping
    public List<RepairRequestDTO> getAll() {
        return repairRequestService.getAll();
    }

    @GetMapping("/{id}")
    public RepairRequestDTO getById(@PathVariable Long id) {
        return repairRequestService.getById(id);
    }

    @PostMapping
    public ResponseEntity<RepairRequestDTO> create(@Valid @RequestBody RepairRequestDTO request) {
        RepairRequestDTO created = repairRequestService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PatchMapping("/{id}")
    public RepairRequestDTO update(@PathVariable Long id, @Valid @RequestBody RepairRequestDTO request) {
        return repairRequestService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repairRequestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
