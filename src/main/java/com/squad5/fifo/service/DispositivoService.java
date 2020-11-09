package com.squad5.fifo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.squad5.fifo.dto.DispositivoInsertDTO;
import com.squad5.fifo.dto.DispositivoUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.squad5.fifo.dto.DispositivoDTO;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.repository.DispositivoRepository;

@Service @RequiredArgsConstructor
public class DispositivoService {
	
	private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum dispositivo com o id fornecido foi encontrado.";

	private final DispositivoRepository dispositivoRepository;

	private final ModelMapper modelMapper;
	
	public DispositivoDTO findById(Long id) {
		return dispositivoToDispositivoDTO(validateId(id));
	}

	public List<DispositivoDTO> findAll() {
		return dispositivoRepository.findAll().stream()
				.map(this::dispositivoToDispositivoDTO)
				.collect(Collectors.toList());
	}

	public DispositivoDTO insert(DispositivoInsertDTO dispositivoInsertDTO) {
		Dispositivo dispositivo = dispositivoDTOToDispositivo(dispositivoInsertDTO);
		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	public DispositivoDTO update(DispositivoUpdateDTO dispositivoUpdateDTO) {
		Dispositivo dispositivo = validateId(dispositivoUpdateDTO.getId());
		modelMapper.map(dispositivoUpdateDTO, dispositivo);
		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	public void deleteById(long id) {
		validateId(id);
		dispositivoRepository.deleteById(id);
	}

	private Dispositivo validateId(long id) {
		return dispositivoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}

	private DispositivoDTO dispositivoToDispositivoDTO(Dispositivo dispositivo){
		return modelMapper.map(dispositivo, DispositivoDTO.class);
	}

	private Dispositivo dispositivoDTOToDispositivo(DispositivoDTO dispositivoDTO){
		return modelMapper.map(dispositivoDTO, Dispositivo.class);
	}

}
