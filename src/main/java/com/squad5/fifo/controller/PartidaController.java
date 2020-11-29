package com.squad5.fifo.controller;

import com.squad5.fifo.dto.ParticipacaoDTO;
import com.squad5.fifo.dto.PartidaDTO;
import com.squad5.fifo.service.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private static final String MSG_PARTICIPACOES_REPETIDAS = "Há ids de usuários repetidos na lista de participações.";

    private final PartidaService partidaService;

    @PostMapping("/{dispositivoId}")
    public List<ParticipacaoDTO> post(@RequestBody @Valid PartidaDTO partidaDTO) {
        if(partidaDTO.getPaticipacaoList().stream()
                .map(ParticipacaoDTO::getUsuario)
                .distinct().count()
                < partidaDTO.getPaticipacaoList().size())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_PARTICIPACOES_REPETIDAS);

        return partidaService.informarResultados(partidaDTO);
    }

}
