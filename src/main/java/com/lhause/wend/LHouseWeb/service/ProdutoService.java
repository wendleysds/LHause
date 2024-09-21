package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.ProdutoEntity;
import com.lhause.wend.LHouseWeb.data.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoEntity createProduto(ProdutoEntity produto){
        produto.setId(null);
        produtoRepository.save(produto);
        return produto;
    }
    
    public ProdutoEntity findProdutoById(Integer id) {
        return produtoRepository.findById(id).orElse(null);
    }
    
    public List<ProdutoEntity> findAllProdutos(){
        return produtoRepository.findAll();
    }
    
    public ProdutoEntity updateProduto(Integer id, ProdutoEntity produtoRequest){
        ProdutoEntity produto = findProdutoById(id);
        
        produto.setEstoque(produtoRequest.getEstoque());
        produto.setNome(produtoRequest.getNome());
        produto.setTipo(produtoRequest.getTipo());
        produto.setValorUnitario(produtoRequest.getValorUnitario());
        
        produtoRepository.save(produto);
        return produto;
    }
    
    public void deleteProduto(Integer id){
        produtoRepository.deleteById(findProdutoById(id).getId());
    }
}
