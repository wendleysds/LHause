package com.lhause.wend.LHouseWeb.restController;

import static com.lhause.wend.LHouseWeb.Utils.CheckUser.*;
import com.lhause.wend.LHouseWeb.model.CarrinhoProduto;
import com.lhause.wend.LHouseWeb.model.Compra;
import com.lhause.wend.LHouseWeb.model.Venda;
import com.lhause.wend.LHouseWeb.model.VendaPesquisa;
import com.lhause.wend.LHouseWeb.service.VendaService;
import com.lhause.wend.LHouseWeb.service.ProdutoService;
import com.lhause.wend.LHouseWeb.service.ProdutoVendaService;
import com.lhause.wend.LHouseWeb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wendley S
 */
@RestController
@RequestMapping("/api/venda")
public class VendaAPIController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProdutoVendaService produtoVendaService;

    @GetMapping("/{id}")
    public ResponseEntity searchVendaById(HttpServletRequest request, @PathVariable("id") Integer id) {
        if (userIsNotLogged(request) || userRoleIsCliente(request)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        Venda venda = vendaService.findVendaById(id);
        if(venda == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        
        VendaPesquisa vendaPesquisa = new VendaPesquisa();
        
        var p = new ArrayList<CarrinhoProduto>();
        for(var pve : venda.getProdutos()){
            p.add(new CarrinhoProduto(
                    produtoService.findProdutoById(pve.getProdutoId()),
                    pve.getQuantidade()
            ));
        }
        var cliente = userService.findUserById(venda.getClienteId());
        
        vendaPesquisa.setClienteId(cliente.getId());
        vendaPesquisa.setClienteNome(cliente.getNome());
        vendaPesquisa.setData(venda.getData());
        vendaPesquisa.setProdutos(p);
        
        return ResponseEntity.ok(vendaPesquisa);
    }

    @PostMapping("/finalizar")
    public ResponseEntity createVenda(HttpServletRequest request, @RequestBody Compra compra) {
        if (userIsNotLogged(request)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        if (compra.getProdutos().size() <= 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        var user = getUser(request);

        var venda = new Venda();

        venda.setClienteId(user.getId());
        venda.setCompra(compra);
        venda.setData(Date.valueOf(LocalDate.now()));
        venda.setFuncionarioId(18);
        var vendaId = vendaService.createVenda(venda).getId();

        for (var produtos : compra.getProdutos()) {
            produtoVendaService.createProdutoVenda(produtos.getQuantidade(), produtos.getProduto().getId(), vendaId);
            produtoService.updateEstoque(produtos.getProduto().getId(), (produtos.getProduto().getEstoque() - produtos.getQuantidade()));
        }

        return new ResponseEntity(vendaId,HttpStatus.CREATED);
    }
}
