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
@Table(name = "produto_venda")
public class ProdutoVendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;
    @Column(name = "venda_id", nullable = false)
    private Integer vendaId;
}
