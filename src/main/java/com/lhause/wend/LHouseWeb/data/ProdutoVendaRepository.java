package com.lhause.wend.LHouseWeb.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Wendley S
 */
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVendaEntity, Integer>{
    @Query(value="select * from produto_venda where venda_id = ?1", nativeQuery=true) 
    List<ProdutoVendaEntity> findAllProdutosInVenda(Integer vendaId);
}
