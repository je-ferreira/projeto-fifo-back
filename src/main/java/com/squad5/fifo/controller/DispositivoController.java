package com.squad5.fifo.controller;

import java.util.List;

import com.squad5.fifo.dto.DispositivoInsertDTO;
import com.squad5.fifo.dto.DispositivoUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad5.fifo.dto.DispositivoDTO;
import com.squad5.fifo.service.DispositivoService;

import javax.validation.Valid;

@RestController @RequestMapping("/dispositivos")
@RequiredArgsConstructor
public class DispositivoController {

	private final DispositivoService dispositivoService;
	
	@GetMapping("/{id}")
	public DispositivoDTO get(@PathVariable Long id) {
		return dispositivoService.findById(id);
	}
	
	@GetMapping
	public List<DispositivoDTO> getAll() {
		return dispositivoService.findAll();
	}
	
	@PostMapping
	public DispositivoDTO post(@RequestBody @Valid DispositivoInsertDTO dispositivoInsertDTO) {
		return dispositivoService.insert(dispositivoInsertDTO);
	}
	
	@PutMapping
	public DispositivoDTO put(@RequestBody @Valid DispositivoUpdateDTO dispositivoUpdateDTO) {
		return dispositivoService.update(dispositivoUpdateDTO);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		dispositivoService.deleteById(id);
	}
}
