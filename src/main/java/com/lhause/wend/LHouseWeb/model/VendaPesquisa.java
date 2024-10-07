package com.lhause.wend.LHouseWeb.model;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class VendaPesquisa {

    private Integer id;
    private Integer clienteId;
    private String ClienteNome;
    private Date data;
    private List<CarrinhoProduto> produtos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return ClienteNome;
    }

    public void setClienteNome(String ClienteNome) {
        this.ClienteNome = ClienteNome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<CarrinhoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CarrinhoProduto> produtos) {
        this.produtos = produtos;
    }
}
