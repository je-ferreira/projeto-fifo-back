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

import com.squad5.fifo.dto.TipoDispositivoDTO;
import com.squad5.fifo.dto.TipoDispositivoInsertDTO;
import com.squad5.fifo.dto.TipoDispositivoUpdateDTO;
import com.squad5.fifo.service.TipoDispositivoService;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/tiposDispositivo")
@RequiredArgsConstructor
public class TipoDispositivoController {

	private final TipoDispositivoService tipoDispositivoService;
	
	@GetMapping("/{id}")
	public TipoDispositivoDTO getById(@PathVariable Long id) {
		return tipoDispositivoService.findById(id);
	}
	
	@GetMapping
	public List<TipoDispositivoDTO> getAll() {
		return tipoDispositivoService.findAll();
	}
	
	@PostMapping
	public TipoDispositivoDTO post(@RequestBody @Valid TipoDispositivoInsertDTO tipoDispositivoInsertDTO) {
		return tipoDispositivoService.insert(tipoDispositivoInsertDTO);
	}
	
	@PutMapping
	public TipoDispositivoDTO put(@RequestBody @Valid TipoDispositivoUpdateDTO tipoDispositivoUpdateDTO) {
		return tipoDispositivoService.update(tipoDispositivoUpdateDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		tipoDispositivoService.deleteById(id);
	}
}
