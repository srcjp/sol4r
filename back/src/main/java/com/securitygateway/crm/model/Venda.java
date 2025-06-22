package com.securitygateway.crm.model;

import com.securitygateway.loginboilerplate.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client cliente;
    private String etapa;
    private String modulo;
    private BigDecimal valor;

    @ManyToOne
    private User vendedorResponsavel;
}
