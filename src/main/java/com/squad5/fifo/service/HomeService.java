package com.squad5.fifo.service;

import com.squad5.fifo.dto.HomePartidaAtualDTO;
import com.squad5.fifo.dto.JogoDTO;
import com.squad5.fifo.model.Partida;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class HomeService {

    private final JogoService jogoService;

    private final DispositivoService dispositivoService;

    private final PartidaService partidaService;

    public List<HomePartidaAtualDTO> findPartidasAtuais() {
        return dispositivoService.findAtivosSendoJogados().stream()
                .map(dispositivo -> {
                    Partida partida = partidaService.getPartidaByNode(dispositivo.getAtual().getId());
                    return HomePartidaAtualDTO.builder()
                            .nomeDispositivo(dispositivo.getNome())
                            .idPartida(partida.getId())
                            .idJogo(partida.getJogo().getId())
                            .build();
                }).collect(Collectors.toList());
    }

    public List<JogoDTO> findJogosAtivos() {
        return jogoService.findAtivos().stream()
                .map(jogoService::jogoToJogoDTO)
                .collect(Collectors.toList());
    }

}
