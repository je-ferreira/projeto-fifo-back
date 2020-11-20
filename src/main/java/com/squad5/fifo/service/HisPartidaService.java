package com.squad5.fifo.service;

import com.squad5.fifo.model.HisNode;
import com.squad5.fifo.model.HisPartida;
import com.squad5.fifo.model.Partida;
import com.squad5.fifo.repository.HisPartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class HisPartidaService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhuma partida em histórico com o id fornecido foi encontrado.";

    private final HisPartidaRepository hisPartidaRepository;

    HisPartida findModelById(Long id) {
        return hisPartidaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<HisPartida> findAllModels() {
        return hisPartidaRepository.findAll();
    }

    HisPartida insertNewModel(Partida partida, HisNode hisNode) {
        return hisPartidaRepository.save(HisPartida.builder()
                .id(partida.getId())
                .jogo(partida.getJogo())
                .resultado(partida.getResultado())
                .hisNode(hisNode)
                .build());
    }

}
