package com.securitygateway.crm.controller;

import com.securitygateway.crm.model.Client;
import com.securitygateway.crm.repository.ClientRepository;
import com.securitygateway.loginboilerplate.model.Gender;
import com.securitygateway.loginboilerplate.model.Role;
import com.securitygateway.loginboilerplate.model.User;
import com.securitygateway.loginboilerplate.model.Username;
import com.securitygateway.loginboilerplate.repository.UserRepository;
import com.securitygateway.loginboilerplate.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @GetMapping
    public List<Client> all() {
        return clientRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        client.setDataCriacao(LocalDateTime.now());
        client.setUltimaAtualizacao(LocalDateTime.now());
        Client saved = clientRepository.save(client);

        if (client.getEmail() != null && client.getCpf() != null) {
            String rawPassword = "a" + client.getCpf() + "!";
            User user = User.builder()
                    .name(new Username("Cliente", client.getCpf()))
                    .email(client.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(rawPassword))
                    .gender(Gender.MALE)
                    .role(Role.USER)
                    .isVerified(true)
                    .build();
            userRepository.save(user);
            try {
                emailService.sendWelcomeEmail(client.getEmail(), rawPassword);
            } catch (Exception ignored) {
            }
        }

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client updated) {
        return clientRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    updated.setDataCriacao(existing.getDataCriacao());
                    updated.setUltimaAtualizacao(LocalDateTime.now());
                    return ResponseEntity.ok(clientRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
