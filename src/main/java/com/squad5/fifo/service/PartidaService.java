package com.squad5.fifo.service;

import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.model.Node;
import com.squad5.fifo.model.Partida;
import com.squad5.fifo.repository.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class PartidaService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhuma partida com o id fornecido foi encontrada.";
    private static final String MSG_PARTIDA_NAO_ENCONTRADA_NODE = "Não foi encontrada uma partida relacionada a um node com esse id.";

    private final PartidaRepository partidaRepository;

    Partida findModelById(Long id) {
        return partidaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Partida> findAllModels() {
        return partidaRepository.findAll();
    }

    Partida insertNewModel(Jogo jogo, Node node) {
        return partidaRepository.save(Partida.builder()
                .jogo(jogo)
                .node(node)
                .build());
    }

    Partida updateModel(Long id, String resultado) {
        Partida partida = findModelById(id);
        partida.setResultado(resultado);
        return partidaRepository.save(partida);
    }

    void deleteById(Long id) {
        findModelById(id);
        partidaRepository.deleteById(id);
    }

    Partida getPartidaByNode(Long id) {
        return partidaRepository.findByNodeId(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PARTIDA_NAO_ENCONTRADA_NODE)
        );
    }

}
