package com.lhause.wend.LHouseWeb.model;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class VendaPesquisa {
    private Integer vendaID;
    private Integer idCliente;
    private String nomeCliente;
    private Venda venda;
    private List<CarrinhoProduto> produtos;

    public Integer getVendaID() {
        return vendaID;
    }

    public void setVendaID(Integer vendaID) {
        this.vendaID = vendaID;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<CarrinhoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CarrinhoProduto> produtos) {
        this.produtos = produtos;
    }

    
}
