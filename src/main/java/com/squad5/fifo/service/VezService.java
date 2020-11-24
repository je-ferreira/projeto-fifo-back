package com.squad5.fifo.service;

import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.model.Vez;
import com.squad5.fifo.repository.VezRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class VezService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhuma vez com o id fornecido foi encontrada.";

    private final VezRepository vezReporsitory;

    private final ModelMapper modelMapper;

    Vez findModelById(Long id) {
        return vezReporsitory.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Vez> findAllModels() {
        return vezReporsitory.findAll();
    }

    Vez insertNewModel(Jogo jogo) {
        return vezReporsitory.save(Vez.builder()
                .jogo(jogo)
                .build());
    }

    Vez updateModel(Vez vez) {
        Vez vezAtual = findModelById(vez.getId());
        vez.setJogo(vez.getJogo());
        modelMapper.map(vez, vezAtual);
        return vezReporsitory.save(vezAtual);
    }

    void deleteById(Long id) {
        findModelById(id);
        vezReporsitory.deleteById(id);
    }

}
