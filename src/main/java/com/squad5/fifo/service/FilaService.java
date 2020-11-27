package com.squad5.fifo.service;

import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.model.Vez;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class FilaService {

    private final ParticipacaoService participacaoService;

    private final VezService vezService;

    private final UsuarioService usuarioService;

    private final DispositivoService dispositivoService;

    void atualizar(Dispositivo dispositivo) {
        Optional<Vez> vezOptional = vezService.findPrimeiroDaFila(dispositivo);
        if(!vezOptional.isPresent()) return;
        Vez vez = vezOptional.get();
        vez.setSaida(new Date());
        vez = vezService.updateModel(vez);
        Long vezId = vez.getId();

        usuarioService.findByVez(vez).forEach(usuario -> participacaoService.insertNewModel(vezId, usuario.getId()));
    }

    Dispositivo procuraDispositivo(Jogo jogo) {
        return dispositivoService.findFirstByTipoDispositivo(jogo);
    }

    @PostConstruct
    void init(){
        vezService.setFilaService(this);
    }

}
