package com.lhause.wend.LHouseWeb.data;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wendley S
 */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer>{
    @Modifying
    @Transactional
    @Query(value="update produto set estoque = ?1 where id = ?2", nativeQuery=true) 
    Integer updateEstoque(Integer estoque, Integer produtoId);
}
