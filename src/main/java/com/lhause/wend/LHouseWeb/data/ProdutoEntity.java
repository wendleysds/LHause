package com.lhause.wend.LHouseWeb.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author Wendley S
 */
@Data
@Entity
@Table(name = "produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @Column(name = "valor_unitario", nullable = false)
    private double valorUnitario;
    @Column(name = "estoque", nullable = false)
    private Integer estoque;
}
