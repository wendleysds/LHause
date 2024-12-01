package com.lhause.wend.LHouseWeb.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Wendley S
 */
@Repository
public interface ComputadorRepository extends JpaRepository<ComputadorEntity, Integer>{
    
}
