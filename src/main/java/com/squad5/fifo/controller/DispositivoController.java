package com.squad5.fifo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

@RestController @RequestMapping("/dispositivos")
public class DispositivoController {

	@Autowired
	private DispositivoService service;
	
	@GetMapping("/{id}")
	public DispositivoDTO get(@PathVariable long id) {
		return service.findById(id);				
	}
	
	@GetMapping
	public List<DispositivoDTO> getAll() {
		return service.findAll();				
	}
	
	@PostMapping
	public DispositivoDTO post(@RequestBody @Validated DispositivoDTO dto) {
		return service.insert(dto);				
	}
	
	@PutMapping
	public DispositivoDTO put(@RequestBody DispositivoDTO dto) {
		return service.update(dto);				
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		service.deleteById(id);			
	}
}
