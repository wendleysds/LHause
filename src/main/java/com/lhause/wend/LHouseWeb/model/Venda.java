package com.lhause.wend.LHouseWeb.model;

import com.lhause.wend.LHouseWeb.data.ProdutoVendaEntity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class Venda {

    private int id;
    private Date data;
    private Compra compra;
    private int clienteId;
    private int funcionarioId; 
    private List<ProdutoVendaEntity> produtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }
    
    public List<ProdutoVendaEntity> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVendaEntity> produtos) {
        this.produtos = produtos;
    }   
}
