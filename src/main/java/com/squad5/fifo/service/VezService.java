package com.squad5.fifo.service;

import com.squad5.fifo.dto.ConviteAceitoDTO;
import com.squad5.fifo.dto.ConviteInsertDTO;
import com.squad5.fifo.dto.UsuarioUpdateDTO;
import com.squad5.fifo.dto.VezDTO;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.model.Usuario;
import com.squad5.fifo.model.Vez;
import com.squad5.fifo.repository.VezRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class VezService {

    private static final String MSG_ID_NAO_ENCONTRADO = "Nenhuma vez com o id fornecido foi encontrada.";
    private static final String MSG_USUARIO_NAO_CONVIDADO = "O usuário como id fornecido não foi convidado para essa partida.";
    private static final String MSG_USUARIO_OCUPADO = "O usuário não pode aceitar convites.";
    private static final String MSG_HA_CONVITES_PENDENTES = "Ainda há convites pendentes.";

    private final VezRepository vezReporsitory;

    private final JogoService jogoService;

    private final UsuarioService usuarioService;

    private final ModelMapper modelMapper;

    @Setter(AccessLevel.PACKAGE)
    private FilaService filaService;

    public VezDTO convidar(ConviteInsertDTO convitInsertDTO) {
        Jogo jogo = jogoService.findModelById(convitInsertDTO.getJogo());
        Usuario usuario = usuarioService.findModelById(convitInsertDTO.getConvidante());
        List<Usuario> usuarioList = convitInsertDTO.getConvidadoList().stream()
                .map(usuarioService::findModelById)
                .collect(Collectors.toList());

        Vez vez = insertNewModel(jogoService.findModelById(jogo.getId()));
        usuario.setVez(vez);
        vez.setConvidante(usuario);
        usuarioService.update(modelMapper.map(usuarioService.usuarioToUsuarioDTO(usuario), UsuarioUpdateDTO.class));
        vez.getUsuarioList().addAll(usuarioList);
        return vezToVezDTO(vezReporsitory.save(vez));
    }

    public VezDTO aceitarConvite(ConviteAceitoDTO conviteAceitoDTO) {
        Vez vez = findModelById(conviteAceitoDTO.getVez());
        Usuario usuario = vez.getUsuarioList().stream().filter(convidado -> conviteAceitoDTO.getUsuario().equals(convidado.getId())).findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_USUARIO_NAO_CONVIDADO)
        );

        if(usuario.getVez() != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_USUARIO_OCUPADO);

        usuario.setVez(vez);
        usuarioService.update(modelMapper.map(usuarioService.usuarioToUsuarioDTO(usuario), UsuarioUpdateDTO.class));
        vez.getUsuarioList().removeIf(convidado -> convidado.getId().equals(usuario.getId()));

        return vezToVezDTO(vezReporsitory.save(vez));
    }

    public VezDTO entrarNaFila(Long vezId) {
        Vez vez = findModelById(vezId);
        if(!vez.getUsuarioList().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_HA_CONVITES_PENDENTES);
        if(vez.getDispositivo() == null) vez.setDispositivo(filaService.procuraDispositivo(vez.getJogo()));
        vez.setEntrada(new Date());

        vez = vezReporsitory.save(vez);

        if(!(vezReporsitory.findDistinctVezByResultadoNullAndDispositivo(vez.getDispositivo()).isPresent()
                || vezReporsitory.findByEntradaGreaterThan(vez.getEntrada()).isPresent())) {
            filaService.atualizar(vez.getDispositivo());
        }

        return vezToVezDTO(vez);
    }

    Vez findModelById(Long id) {
        return vezReporsitory.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
        );
    }

    List<Vez> findAllModels() {
        return vezReporsitory.findAll();
    }

    Vez insertNewModel(Jogo jogo) {
        return vezReporsitory.save(Vez.builder()
                .jogo(jogo)
                .usuarioList(new ArrayList<>())
                .build());
    }

    Vez updateModel(Vez vez) {
        Vez vezAtual = findModelById(vez.getId());
        vez.setJogo(vez.getJogo());
        modelMapper.map(vez, vezAtual);
        vezAtual.setUsuarioList(vez.getUsuarioList());
        return vezReporsitory.save(vezAtual);
    }

    void deleteById(Long id) {
        findModelById(id);
        vezReporsitory.deleteById(id);
    }

    VezDTO vezToVezDTO(Vez vez) {
        VezDTO vezDTO = modelMapper.map(vez, VezDTO.class);
        vezDTO.setJogo(vez.getJogo().getId());
        if(vez.getJogo() != null) vezDTO.setDispositivo(vez.getJogo().getId());
        if(vez.getUsuarioList() != null) vezDTO.setUsuarioList(vez.getUsuarioList().stream()
                .map(Usuario::getId)
                .collect(Collectors.toList())
        );
        return vezDTO;
    }

    List<Vez> findParticipacoesAtuais() {
        return vezReporsitory.findDistinctVezByResultadoNull();
    }

    Optional<Vez> findPrimeiroDaFila(Dispositivo dispositivo) {
        return vezReporsitory.findFirstByDispositivo(dispositivo);
    }

    @PostConstruct
    void init(){
        usuarioService.setVezService(this);
    }

}