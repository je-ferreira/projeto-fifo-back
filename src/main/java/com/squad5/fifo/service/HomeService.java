package com.squad5.fifo.service;

import com.squad5.fifo.dto.HomePartidaAtualDTO;
import com.squad5.fifo.dto.JogoDTO;
import com.squad5.fifo.model.Participacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class HomeService {

    private final JogoService jogoService;

    private final ParticipacaoService participacaoService;

    public List<HomePartidaAtualDTO> findPartidasAtuais() {
        List<HomePartidaAtualDTO> homePartidaAtualDTOList = new ArrayList<>();
        for(Participacao participacao : participacaoService.findParticipacoesAtuais()){
            if(homePartidaAtualDTOList.stream().noneMatch(homePartidaAtualDTO -> homePartidaAtualDTO.getIdVez().equals(participacao.getVez().getId())))
                homePartidaAtualDTOList.add(HomePartidaAtualDTO.builder()
                        .idVez(participacao.getVez().getId())
                        .nomeDispositivo(participacao.getVez().getDispositivo().getNome())
                        .idJogo(participacao.getVez().getJogo().getId())
                        .nomeJogo(participacao.getVez().getJogo().getNome())
                        .build());
        }
        return homePartidaAtualDTOList;
    }

    public List<JogoDTO> findJogosAtivos() {
        return jogoService.findAtivos().stream()
                .map(jogoService::jogoToJogoDTO)
                .collect(Collectors.toList());
    }

}
