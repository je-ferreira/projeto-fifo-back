package com.squad5.fifo.service;

import com.squad5.fifo.model.Metodo;
import com.squad5.fifo.model.TipoMetodo;
import com.squad5.fifo.repository.MetodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service @RequiredArgsConstructor
public class MetodoService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum metodo com o id fornecido foi encontrado.";

    private final MetodoRepository metodoRepository;

    Metodo findModelById(Long id) {
        return metodoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Metodo> findAllModels() {
        return metodoRepository.findAll();
    }

    Metodo insertNewModel(TipoMetodo tipoMetodo) {
        return metodoRepository.save(Metodo.builder()
                .tipoMetodo(tipoMetodo)
                .build());
    }

    Metodo updateModel(Long id, String estado) {
        Metodo metodo = findModelById(id);
        metodo.setEstado(estado);
        return metodoRepository.save(metodo);
    }

    void deleteById(Long id) {
        findModelById(id);
        metodoRepository.deleteById(id);
    }

}
