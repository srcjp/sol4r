package com.securitygateway.crm.repository;

import com.securitygateway.crm.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
