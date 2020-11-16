package com.squad5.fifo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad5.fifo.dto.JogoDTO;
import com.squad5.fifo.dto.JogoInsertDTO;
import com.squad5.fifo.dto.JogoUpdateDTO;
import com.squad5.fifo.service.JogoService;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

	private final JogoService jogoService;
	
	@GetMapping("/{id}")
	public JogoDTO get(@PathVariable Long id) {
		return jogoService.findById(id);
	}

	@GetMapping
	public List<JogoDTO> getAll() {
		return jogoService.findAll();
	}

	@PostMapping
	public JogoDTO post(@RequestBody @Valid JogoInsertDTO jogoInsertDTO) {
		return jogoService.insert(jogoInsertDTO);
	}

	@PutMapping
	public JogoDTO put(@RequestBody @Valid JogoUpdateDTO jogoUpdateDTO) {
		return jogoService.update(jogoUpdateDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		jogoService.deleteById(id);
	}

	@PutMapping("/{jogoId}/tiposDispositivo/add/{tipoDispositivoId}")
	public JogoDTO addTipoDispositivo(@PathVariable Long jogoId, @PathVariable Long tipoDispositivoID){
		return jogoService.addTipoDispositivo(jogoId, tipoDispositivoID);
	}

	@PutMapping("/{jogoId}/tiposDispositivo/remove/{tipoDispositivoId}")
	public JogoDTO removeTipoDispositivo(@PathVariable Long jogoId, @PathVariable Long tipoDispositivoID){
		return jogoService.removeTipoDispositivo(jogoId, tipoDispositivoID);
	}

}
