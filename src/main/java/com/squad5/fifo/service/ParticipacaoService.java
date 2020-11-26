package com.squad5.fifo.service;

import com.squad5.fifo.model.Participacao;
import com.squad5.fifo.model.ParticipacaoId;
import com.squad5.fifo.repository.ParticipacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class ParticipacaoService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhuma participação com o id fornecido foi encontrada.";
    private static final String MSG_RESULTADO_JA_CADASTRADO = "Já existe um cadastro referente a essa participação do usuário.";

    private final ParticipacaoRepository participacaoRepository;

    private final ModelMapper modelMapper;

    Participacao findModelById(Long vezId, Long usuarioId) {
        return participacaoRepository.findById(new ParticipacaoId(vezId, usuarioId)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Participacao> findAllModels() {
        return participacaoRepository.findAll();
    }

    Participacao insertNewModel(Long vezId, Long usuarioId) {
        ParticipacaoId participacaoId = ParticipacaoId.builder()
                .vez(vezId)
                .usuario(usuarioId)
                .build();
        if(participacaoRepository.findById(participacaoId).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_RESULTADO_JA_CADASTRADO);
        return participacaoRepository.save(Participacao.builder()
                .id(participacaoId)
                .build());
    }

    Participacao updateModel(Participacao participacao) {
        Participacao participacaoAtual = findModelById(participacao.getId().getVez(), participacao.getId().getUsuario());
        modelMapper.map(participacao, participacaoAtual);
        return participacaoRepository.save(participacaoAtual);
    }

    void deleteById(Long vezId, Long usuarioId) {
        participacaoRepository.deleteById(findModelById(vezId, usuarioId).getId());
    }

}
