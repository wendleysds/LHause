package com.lhause.wend.LHouseWeb.service;

import com.lhause.wend.LHouseWeb.data.AgendaEntity;
import com.lhause.wend.LHouseWeb.data.AgendaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wendley S
 */
@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public AgendaEntity createAgenda(AgendaEntity agenda) {
        agenda.setId(null);
        agendaRepository.save(agenda);
        return agenda;
    }
    
    public AgendaEntity findAgendaById(Integer id) {
        return agendaRepository.findById(id).orElse(null);
    }

    public List<AgendaEntity> findAllByAgendaByUserId(Integer userId) {
        return agendaRepository.findAllByAgendaByUserId(userId);
    }

    public List<AgendaEntity> findAllAgendas(){
        return agendaRepository.findAll();
    }
    
    public AgendaEntity updateAgenda(Integer id, AgendaEntity agendaRequest) {
        AgendaEntity agenda = findAgendaById(id);

        agenda.setComputadorId(agendaRequest.getComputadorId());
        agenda.setData(agendaRequest.getData());
        agenda.setTempo(agendaRequest.getTempo());
        agenda.setUsuarioId(agendaRequest.getUsuarioId());

        agendaRepository.save(agenda);

        return agenda;
    }

    public List<AgendaEntity> getAllAgendaByUserId(Integer userId) {
        return agendaRepository.findAll();
    }

    public void deleteAgenda(Integer id) {
        agendaRepository.deleteById(findAgendaById(id).getId());
    }
}
