package com.securitygateway.crm.controller;

import com.securitygateway.crm.model.Venda;
import com.securitygateway.crm.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaRepository vendaRepository;

    @GetMapping
    public List<Venda> all() {
        return vendaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @PostMapping
    public ResponseEntity<Venda> create(@RequestBody Venda venda) {
        return new ResponseEntity<>(vendaRepository.save(venda), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> get(@PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda updated) {
        return vendaRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(vendaRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!vendaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
