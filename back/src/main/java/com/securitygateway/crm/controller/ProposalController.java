package com.securitygateway.crm.controller;

import com.securitygateway.crm.model.Proposal;
import com.securitygateway.crm.repository.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalRepository proposalRepository;

    @GetMapping
    public List<Proposal> all() {
        return proposalRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping
    public ResponseEntity<Proposal> create(@RequestBody Proposal proposal) {
        return new ResponseEntity<>(proposalRepository.save(proposal), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proposal> get(@PathVariable Long id) {
        return proposalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proposal> update(@PathVariable Long id, @RequestBody Proposal updated) {
        return proposalRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(proposalRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!proposalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        proposalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
