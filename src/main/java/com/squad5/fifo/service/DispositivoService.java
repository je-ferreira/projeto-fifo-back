package com.squad5.fifo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.squad5.fifo.dto.DispositivoDTO;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum dispositivo com o id fornecido foi encontrado.";
	
	@Autowired
	private DispositivoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Dispositivo validateId(long id) {
		Optional<Dispositivo> optional = repository.findById(id);
		
		if (!repository.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO);
		}
		
		return optional.get();
	}
	
	public DispositivoDTO findById(long id) {
		return new DispositivoDTO(validateId(id));
	}
	
	public List<DispositivoDTO> findAll() {
		return repository.findAll().stream()
				.map(d -> new DispositivoDTO(d))
				.collect(Collectors.toList());
	}
	
	public DispositivoDTO insert(DispositivoDTO dto) {
		Dispositivo d = modelMapper.map(dto, Dispositivo.class);
		return new DispositivoDTO(repository.save(d));
	}
	
	public DispositivoDTO update(DispositivoDTO dto) {
		Dispositivo d = validateId(dto.getId());
		return new DispositivoDTO(repository.save(d));
	}
	
	public void deleteById(long id) {
		validateId(id);
		repository.deleteById(id);
	}
}
