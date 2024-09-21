package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.ProdutoVendaEntity;
import com.lhause.wend.LHouseWeb.data.ProdutoVendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class ProdutoVendaService {

    @Autowired
    ProdutoVendaRepository produtoVendaRepository;

    public ProdutoVendaEntity createProdutoVenda(ProdutoVendaEntity produtoVenda) {
        produtoVenda.setId(null);
        produtoVendaRepository.save(produtoVenda);
        return produtoVenda;
    }

    public List<ProdutoVendaEntity> findAllProdutoVendaByVendaId(Integer vendaId) {
        return produtoVendaRepository.findAllProdutosInVenda(vendaId);
    }

    public ProdutoVendaEntity findProdutoVendById(Integer id) {
        return produtoVendaRepository.findById(id).orElse(null);
    }

    public ProdutoVendaEntity updateProdutoVenda(Integer id, ProdutoVendaEntity produtoVendaRequest) {
        ProdutoVendaEntity produtoVenda = findProdutoVendById(id);
        
        produtoVenda.setProdutoId(produtoVendaRequest.getProdutoId());
        produtoVenda.setQuantidade(produtoVendaRequest.getQuantidade());
        produtoVenda.setVendaId(produtoVendaRequest.getVendaId());
        
        produtoVendaRepository.save(produtoVenda);
        return produtoVenda;
    }

    public void deleteProdutosInVenda(Integer vendaId) {
        List<ProdutoVendaEntity> produtos = findAllProdutoVendaByVendaId(vendaId);

        for (ProdutoVendaEntity p : produtos) {
            produtoVendaRepository.deleteById(findProdutoVendById(p.getId()).getId());
        }
    }

    public void delteProdutoVendById(Integer id) {
        produtoVendaRepository.deleteById(findProdutoVendById(id).getId());
    }
}
