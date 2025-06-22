package com.securitygateway.crm.repository;

import com.securitygateway.crm.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
