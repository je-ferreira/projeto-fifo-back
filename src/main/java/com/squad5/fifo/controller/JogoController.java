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
	public JogoDTO post(@RequestBody @Valid JogoInsertDTO insertDTO) {
		return jogoService.insert(insertDTO);
	}
	
	@PutMapping
	public JogoDTO put(@RequestBody @Valid JogoUpdateDTO updateDTO) {
		return jogoService.update(updateDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		jogoService.deleteById(id);
	}
}
