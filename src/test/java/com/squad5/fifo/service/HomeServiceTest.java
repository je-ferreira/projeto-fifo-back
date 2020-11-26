package com.squad5.fifo.service;

import com.squad5.fifo.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HomeServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    DispositivoService dispositivoService;

    @Autowired
    TipoDispositivoService tipoDispositivoService;

    @Autowired
    JogoService jogoService;

    @Autowired
    VezService vezService;

    @Autowired
    HomeService homeService;

    @Test
    void findPartidasAtuais_ConviteAceito_Contains(){
        UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
        usuarioInsertDTO.setAtivo(true);
        usuarioInsertDTO.setEmail("paulo@email.com");
        usuarioInsertDTO.setNome("paulo");
        UsuarioDTO usuarioDTO = usuarioService.insert(usuarioInsertDTO);

        UsuarioInsertDTO usuarioInsertDTO2 = new UsuarioInsertDTO();
        usuarioInsertDTO2.setAtivo(true);
        usuarioInsertDTO2.setEmail("carlos@email.com");
        usuarioInsertDTO2.setNome("carlos");
        UsuarioDTO usuarioDTO2 = usuarioService.insert(usuarioInsertDTO2);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO.setNome("videoggame");
        TipoDispositivoDTO tipoDispositivoDTO = tipoDispositivoService.insert(tipoDispositivoInsertDTO);

        DispositivoInsertDTO dispositivoInsertDTO = new DispositivoInsertDTO();
        dispositivoInsertDTO.setAtivo(true);
        dispositivoInsertDTO.setNome("hall");
        DispositivoDTO dispositivoDTO = dispositivoService.insert(dispositivoInsertDTO);

        dispositivoService.addTipoDispositivo(dispositivoDTO.getId(), tipoDispositivoDTO.getId());

        JogoInsertDTO jogoInsertDTO = new JogoInsertDTO();
        jogoInsertDTO.setAtivo(true);
        jogoInsertDTO.setNome("fifa");
        JogoDTO jogoDTO = jogoService.insert(jogoInsertDTO);

        jogoService.addTipoDispositivo(jogoDTO.getId(), tipoDispositivoDTO.getId());

        ConviteInsertDTO convitInsertDTO = new ConviteInsertDTO();
        convitInsertDTO.setJogo(jogoDTO.getId());
        convitInsertDTO.setConvidante(usuarioDTO.getId());
        convitInsertDTO.setConvidadoList(new ArrayList<>());
        convitInsertDTO.getConvidadoList().add(usuarioDTO2.getId());
        VezDTO vezDTO = vezService.convidar(convitInsertDTO);

        ConviteAceitoDTO conviteAceitoDTO = new ConviteAceitoDTO();
        conviteAceitoDTO.setVez(vezDTO.getId());
        conviteAceitoDTO.setUsuario(usuarioDTO2.getId());
        vezDTO = vezService.aceitarConvite(conviteAceitoDTO);

        vezDTO = vezService.entrarNaFila(vezDTO.getId());

        assertThat(homeService.findPartidasAtuais().stream().map(HomePartidaAtualDTO::getIdVez)).contains(vezDTO.getId());
    }

}
