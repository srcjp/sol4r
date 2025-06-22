package com.securitygateway.loginboilerplate.controller;

import com.securitygateway.loginboilerplate.model.Role;
import com.securitygateway.loginboilerplate.model.User;
import com.securitygateway.loginboilerplate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        return userRepository.findByDeletedFalse(Sort.by(Sort.Direction.ASC, "name.firstName"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updated) {
        return userRepository.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    if (updated.getPassword() == null) {
                        updated.setPassword(existing.getPassword());
                    }
                    return ResponseEntity.ok(userRepository.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> updateRole(@PathVariable Long id, @RequestBody RoleChangeRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setRole(request.role());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return userRepository
                .findById(id)
                .map(user -> {
                    user.setDeleted(true);
                    userRepository.save(user);
                    // Explicitly set the generic type to avoid Optional inferring Object
                    return ResponseEntity.<Void>noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public record RoleChangeRequest(Role role) {}
}
