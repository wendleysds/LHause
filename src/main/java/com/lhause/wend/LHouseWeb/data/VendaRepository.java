package com.lhause.wend.LHouseWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wendley S
 */
public interface VendaRepository extends JpaRepository<VendaEntity, Integer>{
    @Query(value="select * from agendamento where usuario_id = ?1", nativeQuery=true) 
    List<AgendaEntity> findAllByAgendaByUserId(Integer userId);
}
