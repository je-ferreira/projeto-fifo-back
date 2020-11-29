package com.squad5.fifo.service;

import com.squad5.fifo.dto.ParticipacaoDTO;
import com.squad5.fifo.dto.PartidaDTO;
import com.squad5.fifo.model.Participacao;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.model.Vez;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PartidaService {

    private static final String MSG_INCOMPATIBILIDADE_PARTICIPANTES = "Os participantes informados não são todos e apenas aqueles que estão jogando.";
    private static final String MSG_NAO_HA_PARTIDA_DISPOSITIVO = "Não há uma partida acontecendo nesse dispositivo.";

    private final VezService vezService;

    private final ParticipacaoService participacaoService;

    private final UsuarioService usuarioService;

    private final FilaService filaService;

    private final DispositivoService dispositivoService;

    public List<ParticipacaoDTO> informarResultados(PartidaDTO partidaDTO) {
        Vez vez = vezService.findVezAtual(dispositivoService.findModelById(partidaDTO.getDispositivo()).getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NAO_HA_PARTIDA_DISPOSITIVO)
        );
        List<Participacao> participacaoList = participacaoService.findByVez(vez);
        if(participacaoList.size() != partidaDTO.getPaticipacaoList().size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INCOMPATIBILIDADE_PARTICIPANTES);

        partidaDTO.getPaticipacaoList().sort(Comparator.comparing(ParticipacaoDTO::getUsuario));
        participacaoList.sort(Comparator.comparing(participacao -> participacao.getId().getUsuario()));
        for(int i = 0; i < participacaoList.size(); i++)
            if(participacaoList.get(i).getId().getUsuario().equals(partidaDTO.getPaticipacaoList().get(i).getUsuario()))
                participacaoList.get(i).setResultado(partidaDTO.getPaticipacaoList().get(i).getResultado());
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_INCOMPATIBILIDADE_PARTICIPANTES);

        List<Usuario> usuarioList = usuarioService.findAllById(partidaDTO.getPaticipacaoList().stream()
                .map(ParticipacaoDTO::getUsuario)
                .collect(Collectors.toList()));
        usuarioList.forEach(usuario -> usuario.setVez(null));
        usuarioService.saveAll(usuarioList);
        List<ParticipacaoDTO> participacaoDTOList = participacaoService.saveAll(participacaoList).stream()
                .map(participacaoService::participacaoToParticipacaoDTO)
                .collect(Collectors.toList());

        filaService.atualizar(vez.getDispositivo());

        return participacaoDTOList;
    }

}
