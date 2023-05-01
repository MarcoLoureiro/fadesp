package com.fadesp.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_pagamento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private Integer codigoDoDebito;

    @Column
    private String cpfCnpj;

    @Column
    private String metodoDePagamento;

    @Column
    private Integer numeroDoCartao;

    @Column
    private BigDecimal valorDoPagamento;

    @Column
    private String statusPagamento;

}
