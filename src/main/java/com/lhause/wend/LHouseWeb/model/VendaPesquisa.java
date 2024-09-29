package com.lhause.wend.LHouseWeb.model;

import com.lhause.wend.LHouseWeb.data.UserEntity;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Wendley S
 */
@Component
public class VendaPesquisa {
    
    private UserEntity cliente;
    private Venda venda;
    private List<CarrinhoProduto> produtos;

    public Integer getVendaID() {
        return venda.getId();
    }

    public UserEntity getCliente() {
        return cliente;
    }

    public void setCliente(UserEntity cliente) {
        this.cliente = cliente;
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
