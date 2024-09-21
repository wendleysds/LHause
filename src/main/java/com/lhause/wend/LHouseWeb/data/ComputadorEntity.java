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
@Table(name = "computador")
public class ComputadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "ocupado", nullable = false)
    private Boolean ocupado;
    @Column(name = "ligado", nullable = false)
    private Boolean ligado;
    @Column(name = "especificacoes", nullable = false)
    private String especificacoes;
    @Column(name = "jogos", nullable = false)
    private String jogos;
}
