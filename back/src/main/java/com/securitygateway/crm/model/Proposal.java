package com.securitygateway.crm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String potenciaInstaladaRecomendada;
    private Integer numeroModulos;
    private String inversor;
    private String estrutura;
    private String cabeamento;
    private String outros;
    private BigDecimal valor;
    private Integer parcelas;
    private BigDecimal valorParcelado;
    private String formaPagamento;
    private String metodoPagamento;
    private String garantias;
    private LocalDate validade;
    private LocalDateTime horarioRegistro;
    private Integer visualizacoes;
}
