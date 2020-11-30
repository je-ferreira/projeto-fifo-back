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
    private UsuarioService usuarioService;

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private TipoDispositivoService tipoDispositivoService;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private VezService vezService;

    @Autowired
    private HomeService homeService;

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
        tipoDispositivoInsertDTO.setNome("videogame");
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

        vezDTO = vezService.entrarNaFila(vezDTO.getConvidante());

        assertThat(homeService.findPartidasAtuais().stream().map(HomePartidaAtualDTO::getIdVez)).contains(vezDTO.getId());
    }

    @Test
    void usuariosNaFila_3UsuarioEsperando_IsEquals(){
        UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
        usuarioInsertDTO.setAtivo(true);
        usuarioInsertDTO.setEmail("paulo@email.com2");
        usuarioInsertDTO.setNome("paulo");
        UsuarioDTO usuarioDTO = usuarioService.insert(usuarioInsertDTO);

        UsuarioInsertDTO usuarioInsertDTO2 = new UsuarioInsertDTO();
        usuarioInsertDTO2.setAtivo(true);
        usuarioInsertDTO2.setEmail("carlos@email.com2");
        usuarioInsertDTO2.setNome("carlos");
        UsuarioDTO usuarioDTO2 = usuarioService.insert(usuarioInsertDTO2);

        UsuarioInsertDTO usuarioInsertDTO3 = new UsuarioInsertDTO();
        usuarioInsertDTO3.setAtivo(true);
        usuarioInsertDTO3.setEmail("eduardo@email.com2");
        usuarioInsertDTO3.setNome("eduardo");
        UsuarioDTO usuarioDTO3 = usuarioService.insert(usuarioInsertDTO3);

        UsuarioInsertDTO usuarioInsertDTO4 = new UsuarioInsertDTO();
        usuarioInsertDTO4.setAtivo(true);
        usuarioInsertDTO4.setEmail("ricardo@email.com2");
        usuarioInsertDTO4.setNome("ricardo");
        UsuarioDTO usuarioDTO4 = usuarioService.insert(usuarioInsertDTO4);

        UsuarioInsertDTO usuarioInsertDTO5 = new UsuarioInsertDTO();
        usuarioInsertDTO5.setAtivo(true);
        usuarioInsertDTO5.setEmail("joão@email.com2");
        usuarioInsertDTO5.setNome("joão");
        UsuarioDTO usuarioDTO5 = usuarioService.insert(usuarioInsertDTO5);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO.setNome("videogame2");
        TipoDispositivoDTO tipoDispositivoDTO = tipoDispositivoService.insert(tipoDispositivoInsertDTO);

        DispositivoInsertDTO dispositivoInsertDTO = new DispositivoInsertDTO();
        dispositivoInsertDTO.setAtivo(true);
        dispositivoInsertDTO.setNome("hall2");
        DispositivoDTO dispositivoDTO = dispositivoService.insert(dispositivoInsertDTO);

        dispositivoService.addTipoDispositivo(dispositivoDTO.getId(), tipoDispositivoDTO.getId());

        JogoInsertDTO jogoInsertDTO = new JogoInsertDTO();
        jogoInsertDTO.setAtivo(true);
        jogoInsertDTO.setNome("fifa2");
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

        vezDTO = vezService.entrarNaFila(vezDTO.getConvidante());

        ConviteInsertDTO convitInsertDTO2 = new ConviteInsertDTO();
        convitInsertDTO2.setJogo(jogoDTO.getId());
        convitInsertDTO2.setConvidante(usuarioDTO3.getId());
        VezDTO vezDTO2 = vezService.convidar(convitInsertDTO2);

        ConviteInsertDTO convitInsertDTO3 = new ConviteInsertDTO();
        convitInsertDTO3.setJogo(jogoDTO.getId());
        convitInsertDTO3.setConvidante(usuarioDTO4.getId());
        convitInsertDTO3.setConvidadoList(new ArrayList<>());
        convitInsertDTO3.getConvidadoList().add(usuarioDTO5.getId());
        VezDTO vezDTO3 = vezService.convidar(convitInsertDTO3);

        ConviteAceitoDTO conviteAceitoDTO2 = new ConviteAceitoDTO();
        conviteAceitoDTO2.setVez(vezDTO3.getId());
        conviteAceitoDTO2.setUsuario(usuarioDTO5.getId());
        vezDTO3 = vezService.aceitarConvite(conviteAceitoDTO2);

        vezDTO3 = vezService.entrarNaFila(vezDTO3.getConvidante());

        assertThat(homeService.usuariosNaFila(dispositivoDTO.getId())).isEqualTo(3);
    }

}
