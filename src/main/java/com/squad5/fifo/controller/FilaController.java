package com.squad5.fifo.controller;

import com.squad5.fifo.dto.*;
import com.squad5.fifo.service.FilaService;
import com.squad5.fifo.service.VezService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/filas")
@RequiredArgsConstructor
public class FilaController {

    private final FilaService filaService;

    private final VezService vezService;

    @GetMapping("/{dispositivoId}")
    public List<UsuarioDTO> gerarFila(@PathVariable Long dispositivoId){
        return filaService.gerarFila(dispositivoId);
    }

    @GetMapping("/{dispositivoId}/dadosPagina")
    public FilaPaginaDTO dadosPagina(@PathVariable Long dispositivoId){
        return filaService.dadosPagina(dispositivoId);
    }

    @PostMapping("/convidar")
    public VezDTO convidar(@RequestBody @Valid ConviteInsertDTO conviteInsertDTO){
        return vezService.convidar(conviteInsertDTO);
    }

    @PostMapping("/aceitar")
    public VezDTO aceitarConvite(@RequestBody @Valid ConviteAceitoDTO conviteAceitoDTO){
        return vezService.aceitarConvite(conviteAceitoDTO);
    }

    @GetMapping("/entrar/{idVez}")
    public VezDTO entrarNaFila(@PathVariable Long idVez){
        return vezService.entrarNaFila(idVez);
    }

}
