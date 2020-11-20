package com.squad5.fifo.service;

import com.squad5.fifo.model.HisMetodo;
import com.squad5.fifo.model.Metodo;
import com.squad5.fifo.repository.HisMetodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class HisMetodoService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum método em histórico com o id fornecido foi encontrado.";

    private final HisMetodoRepository hisMetodoRepository;

    HisMetodo findModelById(Long id) {
        return hisMetodoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<HisMetodo> findAllModels() {
        return hisMetodoRepository.findAll();
    }

    HisMetodo insertNewModel(Metodo metodo) {
        return hisMetodoRepository.save(HisMetodo.builder()
                .id(metodo.getId())
                .tipoMetodo(metodo.getTipoMetodo())
                .estado(metodo.getEstado())
                .build());
    }

    HisMetodo updateModel(Long id, String estado) {
        HisMetodo hisMetodo = findModelById(id);
        hisMetodo.setEstado(estado);
        return hisMetodoRepository.save(hisMetodo);
    }

}
