package com.squad5.fifo.service;

import com.squad5.fifo.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PartidaServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoDispositivoService tipoDispositivoService;

    @Autowired
    private DispositivoService dispositivoService;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private VezService vezService;

    @Autowired
    PartidaService partidaService;

    @Test
    void informarResultados_vitoriaEDerrota_Contains(){
        UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
        usuarioInsertDTO.setAtivo(true);
        usuarioInsertDTO.setEmail("paulo@email.com6");
        usuarioInsertDTO.setNome("paulo");
        UsuarioDTO usuarioDTO = usuarioService.insert(usuarioInsertDTO);

        UsuarioInsertDTO usuarioInsertDTO2 = new UsuarioInsertDTO();
        usuarioInsertDTO2.setAtivo(true);
        usuarioInsertDTO2.setEmail("carlos@email.com6");
        usuarioInsertDTO2.setNome("carlos");
        UsuarioDTO usuarioDTO2 = usuarioService.insert(usuarioInsertDTO2);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO.setNome("videogame6");
        TipoDispositivoDTO tipoDispositivoDTO = tipoDispositivoService.insert(tipoDispositivoInsertDTO);

        DispositivoInsertDTO dispositivoInsertDTO = new DispositivoInsertDTO();
        dispositivoInsertDTO.setAtivo(true);
        dispositivoInsertDTO.setNome("hall6");
        DispositivoDTO dispositivoDTO = dispositivoService.insert(dispositivoInsertDTO);

        dispositivoService.addTipoDispositivo(dispositivoDTO.getId(), tipoDispositivoDTO.getId());

        JogoInsertDTO jogoInsertDTO = new JogoInsertDTO();
        jogoInsertDTO.setAtivo(true);
        jogoInsertDTO.setNome("fifa6");
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

        PartidaDTO partidaDTO = new PartidaDTO();
        partidaDTO.setDispositivo(dispositivoDTO.getId());
        List<ParticipacaoDTO> paticipacaoDTOList = new ArrayList<>();
        partidaDTO.setPaticipacaoList(paticipacaoDTOList);

        ParticipacaoDTO participacaoDTO = new ParticipacaoDTO();
        participacaoDTO.setUsuario(usuarioDTO.getId());
        participacaoDTO.setResultado(0);
        paticipacaoDTOList.add(participacaoDTO);

        ParticipacaoDTO participacaoDTO2 = new ParticipacaoDTO();
        participacaoDTO2.setUsuario(usuarioDTO2.getId());
        participacaoDTO2.setResultado(3);
        paticipacaoDTOList.add(participacaoDTO2);

        assertThat(partidaService.informarResultados(partidaDTO).stream()
                .map(ParticipacaoDTO::getResultado)
                .collect(Collectors.toList())).contains(participacaoDTO.getResultado(), participacaoDTO2.getResultado());
    }

}
