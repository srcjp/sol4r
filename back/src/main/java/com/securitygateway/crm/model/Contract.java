package com.securitygateway.crm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client cliente;
    @ManyToOne
    private Proposal proposta;
    private LocalDate dataAssinatura;
    private LocalDate validadeProposta;
    private String status;
    private String formaPagamento;
    private BigDecimal valorTotal;
}
