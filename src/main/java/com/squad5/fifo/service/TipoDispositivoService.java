package com.squad5.fifo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.squad5.fifo.dto.TipoDispositivoDTO;
import com.squad5.fifo.dto.TipoDispositivoInsertDTO;
import com.squad5.fifo.dto.TipoDispositivoUpdateDTO;
import com.squad5.fifo.model.TipoDispositivo;
import com.squad5.fifo.repository.TipoDispositivoRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class TipoDispositivoService {

	//Messages
	private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum tipo de dispositivo com o id fornecido foi encontrado.";
	private static final String MSG_NOME_JA_CADASTRADO = "Já há um tipo de dispositivo com o nome fornecido.";

	//Dependencies
	private final TipoDispositivoRepository tipoDispositivoRepository;
	private final ModelMapper modelMapper;
	
	//CRUD
	public TipoDispositivoDTO findById(Long id) {
		return tipoDispositivoToDTO(validateId(id));
	}
	
	public List<TipoDispositivoDTO> findAll() {
		return tipoDispositivoRepository.findAll().stream()
				.map(this::tipoDispositivoToDTO)
				.collect(Collectors.toList());				
	}
	
	public TipoDispositivoDTO insert(TipoDispositivoInsertDTO insertDTO) {
		if (nomeJaCadastrado(insertDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		TipoDispositivo tipoDispositivo = tipoDispositivoRepository.save(dtoTotipoDispositivo(insertDTO));
		return tipoDispositivoToDTO(tipoDispositivo);
	}
	
	public TipoDispositivoDTO update(TipoDispositivoUpdateDTO tipoDispositivoUpdateDTO) {
		TipoDispositivo tipoDispositivo = validateId(tipoDispositivoUpdateDTO.getId());

		if(tipoDispositivoUpdateDTO.getNome() != null &&
				!tipoDispositivoUpdateDTO.getNome().equals(tipoDispositivo.getNome()) &&
				nomeJaCadastrado(tipoDispositivoUpdateDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		modelMapper.map(tipoDispositivoUpdateDTO, tipoDispositivo);
		return tipoDispositivoToDTO(tipoDispositivo);
	}
	
	public void deleteById(Long id) {
		validateId(id);
		tipoDispositivoRepository.deleteById(id);
	}
	
	//Auxiliary methods
	boolean nomeJaCadastrado(String nome) {
		return tipoDispositivoRepository.findByNome(nome).isPresent();
	}
	
	TipoDispositivo validateId(Long id) {
		return tipoDispositivoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}
	
	TipoDispositivoDTO tipoDispositivoToDTO(TipoDispositivo tipoDispositivo) {
		return modelMapper.map(tipoDispositivo, TipoDispositivoDTO.class);
	}
	
	TipoDispositivo dtoTotipoDispositivo(TipoDispositivoDTO tipoDispositivoDTO) {
		return modelMapper.map(tipoDispositivoDTO, TipoDispositivo.class);
	}

}
