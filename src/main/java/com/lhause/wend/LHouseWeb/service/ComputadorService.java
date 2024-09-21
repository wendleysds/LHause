package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.ComputadorEntity;
import com.lhause.wend.LHouseWeb.data.ComputadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class ComputadorService {

    @Autowired
    private ComputadorRepository computadorRepository;

    public ComputadorEntity createComputador(ComputadorEntity computator){
        computator.setId(null);
        computadorRepository.save(computator);
        return computator;
    }
    
    public ComputadorEntity findComputadorById(Integer id) {
        return computadorRepository.findById(id).orElse(null);
    }

    public List<ComputadorEntity> findAllComputadores() {
        return computadorRepository.findAll();
    }

    public ComputadorEntity updateComputador(Integer id, ComputadorEntity computadorRequest) {
        ComputadorEntity computador = findComputadorById(id);

        computador.setEspecificacoes(computadorRequest.getEspecificacoes());
        computador.setJogos(computadorRequest.getJogos());
        computador.setLigado(computadorRequest.getLigado());
        computador.setOcupado(computadorRequest.getOcupado());

        computadorRepository.save(computador);
        return computador;
    }

    public void deleteComputador(Integer id) {
        computadorRepository.deleteById(findComputadorById(id).getId());
    }
}
