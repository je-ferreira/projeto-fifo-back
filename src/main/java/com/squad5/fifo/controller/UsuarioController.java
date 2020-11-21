package com.squad5.fifo.controller;

import com.squad5.fifo.dto.UsuarioDTO;
import com.squad5.fifo.dto.UsuarioInsertDTO;
import com.squad5.fifo.dto.UsuarioUpdateDTO;
import com.squad5.fifo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public UsuarioDTO get(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return usuarioService.findAll();
    }
    
    @GetMapping("/disponiveis")
    public List<UsuarioDTO> getDisponiveis() {
    	return usuarioService.findDisponiveis();
    }

    @PostMapping
    public UsuarioDTO post(@RequestBody @Valid UsuarioInsertDTO usuarioInsertDTO) {
        return usuarioService.insert(usuarioInsertDTO);
    }

    @PutMapping
    public UsuarioDTO put(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        return usuarioService.update(usuarioUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }

}
