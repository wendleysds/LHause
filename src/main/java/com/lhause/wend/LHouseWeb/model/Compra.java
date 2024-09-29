package com.lhause.wend.LHouseWeb.model;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class Compra {
    private String tipoPagamento;
    private String numeroCartao;
    private Integer numeroParcelas;
    private List<CarrinhoProduto> produtos;
    private Double total;

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public List<CarrinhoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CarrinhoProduto> produtos) {
        this.produtos = produtos;
    }
    
    public Double getTotal() { 
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getTotalCompra() {
        double total = 0;
        for(var produtoCarrinho : produtos){
            total += produtoCarrinho.getTotal();
        }      
        return total;
    }
}
