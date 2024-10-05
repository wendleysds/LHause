package com.lhause.wend.LHouseWeb.model;

import com.lhause.wend.LHouseWeb.data.ProdutoEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class CarrinhoProduto {
    
    private ProdutoEntity produto;
    private Integer quantidade;

    public CarrinhoProduto() {
    }
    
    public CarrinhoProduto(ProdutoEntity produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {     
        return quantidade * produto.getValorUnitario();
    }
}
