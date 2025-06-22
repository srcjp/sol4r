package com.securitygateway.crm.model;

import com.securitygateway.loginboilerplate.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private LocalDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> enderecos = new ArrayList<>();

    private String cpf;
    private String email;
    private String cidade;
    private String origem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposal> propostas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contratos = new ArrayList<>();

    private String notas;
    private Integer visualizacoes;

    @ManyToOne
    private User proprietario;
    private LocalDate validade;
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    private void onCreate() {
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = dataCriacao;
    }

    @PreUpdate
    private void onUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}
