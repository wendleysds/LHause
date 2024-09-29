package com.lhause.wend.LHouseWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wendley S
 */
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer>{
    @Query(value="uptade produto set estoque = ?1 where id = ?2", nativeQuery=true) 
    void updateEstoque(Integer estoque, Integer produtoId);
}
