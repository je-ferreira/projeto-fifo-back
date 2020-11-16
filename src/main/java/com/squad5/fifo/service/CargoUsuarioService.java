package com.squad5.fifo.service;

import com.squad5.fifo.model.CargoUsuario;
import com.squad5.fifo.repository.CargoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service @RequiredArgsConstructor
public class CargoUsuarioService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum cargo de usuário com o id fornecido foi encontrado.";

    private final CargoUsuarioRepository cargoUsuarioRepository;

    CargoUsuario validateId(Long id) {
        return cargoUsuarioRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }
}
