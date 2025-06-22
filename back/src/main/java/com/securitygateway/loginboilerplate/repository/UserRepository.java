package com.securitygateway.loginboilerplate.repository;

import com.securitygateway.loginboilerplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndDeletedFalse(String email);

    List<User> findByDeletedFalse(Sort sort);
}
