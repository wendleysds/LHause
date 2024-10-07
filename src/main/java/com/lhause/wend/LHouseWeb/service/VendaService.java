package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.VendaEntity;
import com.lhause.wend.LHouseWeb.data.VendaRepository;
import com.lhause.wend.LHouseWeb.model.Compra;
import com.lhause.wend.LHouseWeb.model.Venda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaService produtoVendaService;

    public VendaEntity createVenda(Venda venda) {
        VendaEntity vendaEntity = convertVendaToVendaEntity(venda);
        vendaEntity.setId(null);
        
        vendaRepository.save(vendaEntity);
        return vendaEntity;
    }

    public Venda findVendaById(Integer id) {
        VendaEntity vendaEntity = vendaRepository.findById(id).orElse(null);
        if (vendaEntity == null) {
            return null;
        }

        return convertVendaEntityToVenda(vendaEntity);
    }

    public List<Venda> findAllVendas() {
        List<VendaEntity> vendaEntity = vendaRepository.findAll();
        List<Venda> vendas = new ArrayList<>();

        for(var v : vendaEntity){
            vendas.add(convertVendaEntityToVenda(v));
        }

        return vendas;
    }

    public List<Venda> findVendasByClienteId(Integer id){
        List<VendaEntity> vendaEntity = vendaRepository.findVendasByClienteId(id);
        List<Venda> vendas = new ArrayList<>();

        for(var v : vendaEntity){
            vendas.add(convertVendaEntityToVenda(v));
        }

        return vendas;
    }
    
    public Venda convertVendaEntityToVenda(VendaEntity vendaEntity) {
        Venda venda = new Venda();
        venda.setCompra(new Compra());
        venda.setId(vendaEntity.getId());
        venda.setClienteId(vendaEntity.getClienteId());
        venda.setData(vendaEntity.getData());
        venda.setFuncionarioId(vendaEntity.getFuncionarioId());
        venda.getCompra().setNumeroCartao(vendaEntity.getNumeroCartao());
        venda.getCompra().setNumeroParcelas(vendaEntity.getNumeroParcelas());
        venda.getCompra().setTipoPagamento(vendaEntity.getTipoPagamento());
        venda.getCompra().setTotal(vendaEntity.getValor());
        venda.setProdutos(produtoVendaService.findAllProdutoVendaByVendaId(vendaEntity.getId()));
        return venda;
    }
    
    public VendaEntity convertVendaToVendaEntity(Venda venda){
        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setId(venda.getId());
        vendaEntity.setClienteId(venda.getClienteId());
        vendaEntity.setData(venda.getData());
        vendaEntity.setFuncionarioId(venda.getFuncionarioId());
        vendaEntity.setNumeroCartao(venda.getCompra().getNumeroCartao());
        vendaEntity.setNumeroParcelas(venda.getCompra().getNumeroParcelas());
        vendaEntity.setTipoPagamento(venda.getCompra().getTipoPagamento());
        vendaEntity.setValor(venda.getCompra().getTotalCompra());
        return vendaEntity;
    }

    public void deleteVenda(Integer id) {
        vendaRepository.deleteById(findVendaById(id).getId());
    }
}
