package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.ProdutoVendaEntity;
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
    
    public VendaEntity createVenda(Venda venda){
        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setId(null);
        
        vendaEntity.setClienteId(venda.getClienteId());
        vendaEntity.setData(venda.getData());
        vendaEntity.setFuncionarioId(venda.getFuncionarioId());
        vendaEntity.setNumeroCartao(venda.getCompra().getNumeroCartao());
        vendaEntity.setNumeroParcelas(venda.getCompra().getNumeroParcelas());
        vendaEntity.setTipoPagamento(venda.getCompra().getTipoPagamento());
        vendaEntity.setValor(venda.getCompra().getTotalCompra());
        
        vendaRepository.save(vendaEntity);
        return vendaEntity;
    }

    public Venda findVendaById(Integer id){
        VendaEntity vendaEntity = vendaRepository.findById(id).orElse(null);
     
        if(vendaEntity == null) return null;
        
        List<ProdutoVendaEntity> produtoVenda = produtoVendaService.findAllProdutoVendaByVendaId(id);
        
        Venda v = new Venda();
        
        v.setCompra(new Compra());
        v.setId(vendaEntity.getId());
        v.setClienteId(vendaEntity.getClienteId());
        v.setData(vendaEntity.getData());
        v.setFuncionarioId(vendaEntity.getFuncionarioId());
        v.getCompra().setNumeroCartao(vendaEntity.getNumeroCartao());
        v.getCompra().setNumeroParcelas(vendaEntity.getNumeroParcelas());
        v.getCompra().setTipoPagamento(vendaEntity.getTipoPagamento());
        v.getCompra().setTotal(vendaEntity.getValor());
        v.setProdutos(produtoVenda);
        
        return v;
    }
    
    public List<Venda> findAllVendas(){
        List<VendaEntity> vendaEntity = vendaRepository.findAll();
        List<Venda> vendas = new ArrayList<Venda>();
        
        int i = 0;
        for(Venda v : vendas){   
            v.setCompra(new Compra());
            v.setId(vendaEntity.get(i).getId());
            v.setClienteId(vendaEntity.get(i).getClienteId());
            v.setData(vendaEntity.get(i).getData());
            v.setFuncionarioId(vendaEntity.get(i).getFuncionarioId());
            v.getCompra().setNumeroCartao(vendaEntity.get(i).getNumeroCartao());
            v.getCompra().setNumeroParcelas(vendaEntity.get(i).getNumeroParcelas());
            v.getCompra().setTipoPagamento(vendaEntity.get(i).getTipoPagamento());
            v.getCompra().setTotal(vendaEntity.get(i).getValor());
            v.setProdutos(produtoVendaService.findAllProdutoVendaByVendaId(vendaEntity.get(i).getId()));
            i++;
        }
        
        return vendas;
    }
    
    public void deleteVenda(Integer id){
        vendaRepository.deleteById(findVendaById(id).getId());
    }
}
