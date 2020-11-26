package com.squad5.fifo.service;

import com.squad5.fifo.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    void findDisponiveis_UsuarioDisponivel_Contains(){
        UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
        usuarioInsertDTO.setAtivo(true);
        usuarioInsertDTO.setEmail("eduardo@email.com");
        usuarioInsertDTO.setNome("eduardo");
        UsuarioDTO usuarioDTO = usuarioService.insert(usuarioInsertDTO);

        assertThat(usuarioService.findDisponiveis().stream().map(UsuarioDTO::getId)).contains(usuarioDTO.getId());
    }

}
