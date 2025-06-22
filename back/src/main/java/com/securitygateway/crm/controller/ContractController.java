package com.securitygateway.crm.controller;

import com.securitygateway.crm.model.Contract;
import com.securitygateway.crm.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractRepository contractRepository;

    @GetMapping
    public List<Contract> all() {
        return contractRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping
    public ResponseEntity<Contract> create(@RequestBody Contract contract) {
        return new ResponseEntity<>(contractRepository.save(contract), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> get(@PathVariable Long id) {
        return contractRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contract> update(@PathVariable Long id, @RequestBody Contract updated) {
        return contractRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(contractRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!contractRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contractRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
