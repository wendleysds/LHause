package com.lhause.wend.LHouseWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Wendley S
 */
@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Integer>{
    @Query(value="select * from venda where cliente_id = ?1", nativeQuery=true) 
    List<VendaEntity> findVendasByClienteId(Integer userId);
}
