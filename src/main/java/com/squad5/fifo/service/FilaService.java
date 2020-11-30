package com.squad5.fifo.service;

import com.squad5.fifo.dto.ConfirmacaoDTO;
import com.squad5.fifo.dto.FilaPaginaDTO;
import com.squad5.fifo.dto.UsuarioDTO;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.model.Vez;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FilaService {

    private static final String MSG_DISPOSITIVO_TIPO_NAO_EXISTE = "Não há um dispositivo com um tipo do jogo informado.";

    private final ParticipacaoService participacaoService;

    private final VezService vezService;

    private final UsuarioService usuarioService;

    private final DispositivoService dispositivoService;

    private final JogoService jogoService;

    public List<UsuarioDTO> gerarFila(Long dispositivoId) {
        Dispositivo dispositivo = dispositivoService.findModelById(dispositivoId);
        return usuarioService.findByVezDispositivoAndVezEntradaNotNullAndVezSaidaNullOrderByVezEntradaAsc(dispositivo);
    }

    public FilaPaginaDTO dadosPagina(Long dispositivoId) {
        Dispositivo dispositivo = dispositivoService.findModelById(dispositivoId);
        Jogo jogo = jogoService.findAtualByDispositivo(dispositivo).orElse(new Jogo());
        return FilaPaginaDTO.builder()
                .nomeDispositivo(dispositivo.getNome())
                .idJogo(jogo.getId())
                .nomeJogo(jogo.getNome())
                .build();
    }

    public ConfirmacaoDTO vezDadosConfirmacao(Long usuarioId) {
        Usuario usuario = usuarioService.findModelById(usuarioId);
        ConfirmacaoDTO confirmacaoDTO = vezDados(usuario.getVez());
        confirmacaoDTO.getNomeConvidadoList().removeIf(nome -> nome.equals(usuario.getNome()));
        return confirmacaoDTO;
    }

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
        if(jogo.getDispositivoPreferencial() != null) return jogo.getDispositivoPreferencial();
        List<Dispositivo> dispositivoList = dispositivoService.findAllByTipoDispositivo(jogo);
        if(dispositivoList.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_DISPOSITIVO_TIPO_NAO_EXISTE);

        List<Long> sizeFilaList = dispositivoList.stream()
                .map(usuarioService::countByVezDispositivoAndVezEntradaNotNullAndVezSaidaNull)
                .collect(Collectors.toList());
        Dispositivo dispositivo = dispositivoList.get(0);
        Long sizeFila = sizeFilaList.get(0);
        for(int i = 1; i < dispositivoList.size(); i++) if(sizeFilaList.get(i) < sizeFila){
            dispositivo = dispositivoList.get(i);
            sizeFila = sizeFilaList.get(i);
        }
        return dispositivo;
    }

    ConfirmacaoDTO vezDados(Vez vez) {
        return ConfirmacaoDTO.builder()
                .nomeDispositivo(vez.getJogo().getNome())
                .nomeDispositivo(vez.getDispositivo().getNome())
                .nomeConvidadoList(usuarioService.findByVez(vez).stream()
                        .map(Usuario::getNome)
                        .collect(Collectors.toList()))
                .build();
    }

    @PostConstruct
    void init(){
        vezService.setFilaService(this);
    }

}
