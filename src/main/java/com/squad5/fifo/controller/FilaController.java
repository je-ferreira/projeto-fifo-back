package com.squad5.fifo.controller;

import com.squad5.fifo.dto.FilaPaginaDTO;
import com.squad5.fifo.dto.UsuarioDTO;
import com.squad5.fifo.service.FilaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/filas")
@RequiredArgsConstructor
public class FilaController {

    private final FilaService filaService;

    @GetMapping("/{dispositivoId}")
    public List<UsuarioDTO> gerarFila(@PathVariable Long dispositivoId){
        return filaService.gerarFila(dispositivoId);
    }

    @GetMapping("/{dispositivoId}/dadosPagina")
    public FilaPaginaDTO dadosPagina(@PathVariable Long dispositivoId){
        return filaService.dadosPagina(dispositivoId);
    }

}
