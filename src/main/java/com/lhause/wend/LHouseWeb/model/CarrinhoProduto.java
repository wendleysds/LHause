package com.lhause.wend.LHouseWeb.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class CarrinhoProduto {
    
    private Integer id;
    private String nome;
    private String tipo;
    private Double valorUnitario;
    private Double total;
    private Integer quantidade;
    
    public CarrinhoProduto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
