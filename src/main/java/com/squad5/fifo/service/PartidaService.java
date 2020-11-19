package com.squad5.fifo.service;

import com.squad5.fifo.model.Partida;
import com.squad5.fifo.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor
public class PartidaService {

    private static final String MSG_PARTIDA_NAO_ENCONTRADA_NODE = "Não foi encontrada uma partida relacionada a um node com esse id.";

    private final PartidaRepository partidaRepository;

    public Partida getPartidaByNode(Long id) {
        return partidaRepository.findByNodeId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PARTIDA_NAO_ENCONTRADA_NODE)
        );
    }

}
