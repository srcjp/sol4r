package com.securitygateway.crm.controller;

import com.securitygateway.crm.model.Address;
import com.securitygateway.crm.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping
    public List<Address> all() {
        return addressRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address address) {
        return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> get(@PathVariable Long id) {
        return addressRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address updated) {
        return addressRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(addressRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!addressRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        addressRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
