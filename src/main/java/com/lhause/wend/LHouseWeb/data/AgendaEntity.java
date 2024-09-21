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
@Table(name = "agendamento")
public class AgendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "data", nullable = false)
    private Date data;
    @Column(name = "computador_id", nullable = false)
    private Integer computadorId;
    @Column(name = "tempo", nullable = false)
    private Integer tempo;
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
}
