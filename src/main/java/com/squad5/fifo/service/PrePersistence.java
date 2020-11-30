package com.squad5.fifo.service;

import com.squad5.fifo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service @RequiredArgsConstructor
public class PrePersistence {

    private final UsuarioService usuarioService;

    private final TipoDispositivoService tipoDispositivoService;

    private final DispositivoService dispositivoService;

    private final JogoService jogoService;

    @PostConstruct
    private void inti(){
        admin();
        fCamaraDados();
    }

    private void admin() {
        UsuarioInsertDTO usuarioInsertDTO = new UsuarioInsertDTO();
        usuarioInsertDTO.setNome("admin");
        usuarioInsertDTO.setEmail("admin@fi.fo");
        usuarioInsertDTO.setAtivo(false);
        UsuarioDTO usuarioDTO = usuarioService.insert(usuarioInsertDTO);
        usuarioService.updateAdmin(usuarioDTO.getId());
    }

    private void fCamaraDados(){
        TipoDispositivoInsertDTO tipoDispositivoInsertDTO1 = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO1.setNome("PS4");
        TipoDispositivoDTO tipoDispositivoDTO1 = tipoDispositivoService.insert(tipoDispositivoInsertDTO1);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO2 = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO2.setNome("Arcade");
        TipoDispositivoDTO tipoDispositivoDTO2 = tipoDispositivoService.insert(tipoDispositivoInsertDTO2);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO3 = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO3.setNome("Mesa de Sinuca");
        TipoDispositivoDTO tipoDispositivoDTO3 = tipoDispositivoService.insert(tipoDispositivoInsertDTO3);

        TipoDispositivoInsertDTO tipoDispositivoInsertDTO4 = new TipoDispositivoInsertDTO();
        tipoDispositivoInsertDTO4.setNome("Mesa de Ping Pong");
        TipoDispositivoDTO tipoDispositivoDTO4 = tipoDispositivoService.insert(tipoDispositivoInsertDTO4);

        DispositivoInsertDTO dispositivoInsertDTO1 = new DispositivoInsertDTO();
        dispositivoInsertDTO1.setNome("PS4 - Hall de Entrada");
        dispositivoInsertDTO1.setAtivo(true);
        DispositivoDTO dispositivoDTO1 = dispositivoService.insert(dispositivoInsertDTO1);
        dispositivoService.addTipoDispositivo(dispositivoDTO1.getId(), tipoDispositivoDTO1.getId());

        DispositivoInsertDTO dispositivoInsertDTO2 = new DispositivoInsertDTO();
        dispositivoInsertDTO2.setNome("PS4 - Sala Zelda");
        dispositivoInsertDTO2.setAtivo(true);
        DispositivoDTO dispositivoDTO2 = dispositivoService.insert(dispositivoInsertDTO2);
        dispositivoService.addTipoDispositivo(dispositivoDTO2.getId(), tipoDispositivoDTO1.getId());

        DispositivoInsertDTO dispositivoInsertDTO3 = new DispositivoInsertDTO();
        dispositivoInsertDTO3.setNome("Fliperama - Hall de Entrada");
        dispositivoInsertDTO3.setAtivo(true);
        DispositivoDTO dispositivoDTO3 = dispositivoService.insert(dispositivoInsertDTO3);
        dispositivoService.addTipoDispositivo(dispositivoDTO3.getId(), tipoDispositivoDTO2.getId());

        DispositivoInsertDTO dispositivoInsertDTO4 = new DispositivoInsertDTO();
        dispositivoInsertDTO4.setNome("Mesa - Hall de Entrada");
        dispositivoInsertDTO4.setAtivo(true);
        DispositivoDTO dispositivoDTO4 = dispositivoService.insert(dispositivoInsertDTO4);
        dispositivoService.addTipoDispositivo(dispositivoDTO4.getId(), tipoDispositivoDTO3.getId());
        dispositivoService.addTipoDispositivo(dispositivoDTO4.getId(), tipoDispositivoDTO4.getId());

        JogoInsertDTO jogoInsertDTO1 = new JogoInsertDTO();
        jogoInsertDTO1.setNome("FIFA 2020");
        jogoInsertDTO1.setAtivo(true);
        jogoInsertDTO1.setUrlsCapa("https://i.imgur.com/qXQ9TaV.png");
        JogoDTO jogoDTO1 = jogoService.insert(jogoInsertDTO1);
        jogoService.addTipoDispositivo(jogoDTO1.getId(), tipoDispositivoDTO1.getId());
        jogoService.configurarDispositivoPreferencial(jogoDTO1.getId(), dispositivoDTO1.getId());

        JogoInsertDTO jogoInsertDTO2 = new JogoInsertDTO();
        jogoInsertDTO2.setNome("Mortal Kombat");
        jogoInsertDTO2.setAtivo(true);
        jogoInsertDTO2.setUrlsCapa("https://i.imgur.com/SJPtsZn.jpeg");
        JogoDTO jogoDTO2 = jogoService.insert(jogoInsertDTO2);
        jogoService.addTipoDispositivo(jogoDTO2.getId(), tipoDispositivoDTO1.getId());

        JogoInsertDTO jogoInsertDTO3 = new JogoInsertDTO();
        jogoInsertDTO3.setNome("Beat Saber");
        jogoInsertDTO3.setAtivo(true);
        jogoInsertDTO3.setUrlsCapa("https://i.imgur.com/9Nq3nJe.jpeg");
        JogoDTO jogoDTO3 = jogoService.insert(jogoInsertDTO3);
        jogoService.addTipoDispositivo(jogoDTO3.getId(), tipoDispositivoDTO1.getId());

        JogoInsertDTO jogoInsertDTO4 = new JogoInsertDTO();
        jogoInsertDTO4.setNome("Sinuca");
        jogoInsertDTO4.setAtivo(true);
        jogoInsertDTO4.setUrlsCapa("https://i.imgur.com/SDaSNE9.jpeg");
        JogoDTO jogoDTO4 = jogoService.insert(jogoInsertDTO4);
        jogoService.addTipoDispositivo(jogoDTO4.getId(), tipoDispositivoDTO3.getId());

        JogoInsertDTO jogoInsertDTO5 = new JogoInsertDTO();
        jogoInsertDTO5.setNome("Ping Pong");
        jogoInsertDTO5.setAtivo(true);
        jogoInsertDTO5.setUrlsCapa("https://i.imgur.com/YVXf7wl.jpeg");
        JogoDTO jogoDTO5 = jogoService.insert(jogoInsertDTO5);
        jogoService.addTipoDispositivo(jogoDTO5.getId(), tipoDispositivoDTO4.getId());
    }

}
