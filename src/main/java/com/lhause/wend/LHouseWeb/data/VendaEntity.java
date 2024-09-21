package com.lhause.wend.LHouseWeb.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Data;

/**
 *
 * @author Wendley S
 */
@Data
@Entity
@Table(name = "venda")
public class VendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "valor", nullable = false)
    private double valor;
    @Column(name = "cliente_id", nullable = false)
    private Integer clienteId;
    @Column(name = "funcionario_id", nullable = false)
    private Integer funcionarioId;
    @Column(name = "tipo_pagamento", nullable = false)
    private String tipoPagamento;
    @Column(name = "numero_cartao", nullable = false)
    private String numeroCartao;
    @Column(name = "numero_parcelas", nullable = false)
    private Integer numeroParcelas;
}
