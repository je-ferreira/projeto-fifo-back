package com.squad5.fifo.service;

import com.squad5.fifo.dto.HomePartidaAtualDTO;
import com.squad5.fifo.dto.JogoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class HomeService {

    private final JogoService jogoService;

    private final VezService vezService;

    public List<HomePartidaAtualDTO> findPartidasAtuais() {
        return vezService.findParticipacoesAtuais().stream()
                .map(vez -> HomePartidaAtualDTO.builder()
                        .idVez(vez.getId())
                        .nomeDispositivo(vez.getDispositivo().getNome())
                        .idDispositivo(vez.getDispositivo().getId())
                        .idJogo(vez.getJogo().getId())
                        .nomeJogo(vez.getJogo().getNome())
                        .build())
                .collect(Collectors.toList());
    }

    public List<JogoDTO> findJogosAtivos() {
        return jogoService.findAtivos().stream()
                .map(jogoService::jogoToJogoDTO)
                .collect(Collectors.toList());
    }

}
