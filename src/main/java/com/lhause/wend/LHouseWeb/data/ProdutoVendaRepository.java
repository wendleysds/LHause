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
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVendaEntity, Integer>{
    @Query(value="select * from produto_venda where venda_id = ?1", nativeQuery=true) 
    List<ProdutoVendaEntity> findAllProdutosInVenda(Integer vendaId);
}
