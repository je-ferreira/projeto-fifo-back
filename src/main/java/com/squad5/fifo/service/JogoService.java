package com.squad5.fifo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.squad5.fifo.dto.TipoDispositivoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.squad5.fifo.dto.JogoDTO;
import com.squad5.fifo.dto.JogoInsertDTO;
import com.squad5.fifo.dto.JogoUpdateDTO;
import com.squad5.fifo.model.Jogo;
import com.squad5.fifo.repository.JogoRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class JogoService {

	//Messages
	private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum jogo com o id fornecido foi encontrado.";
	private static final String MSG_NOME_JA_CADASTRADO = "Já há um jogo com o nome fornecido.";
	private static final String MSG_ID_TIPO_NAO_ENCONTRADO = "Não há nenhum tipo de dispositivo com esse id vinculado ao jogo.";

	//Dependencies
	private final JogoRepository jogoRepository;
	private final TipoDispositivoService tipoDispositivoService;
	private final ModelMapper modelMapper;
	
	//CRUD
	public JogoDTO findById(Long id) {
		return jogoToJogoDTO(validateId(id));
	}
	
	public List<JogoDTO> findAll() {
		return jogoRepository.findAll().stream()
				.map(this::jogoToJogoDTO)
				.collect(Collectors.toList());				
	}
	
	public JogoDTO insert(JogoInsertDTO insertDTO) {
		if (nomeJaCadastrado(insertDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		Jogo jogo = JogoDTOToJogo(insertDTO);
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public JogoDTO update(JogoUpdateDTO jogoUpdateDTO) {
		Jogo jogo = validateId(jogoUpdateDTO.getId());

		if(jogoUpdateDTO.getNome() != null &&
				!jogoUpdateDTO.getNome().equals(jogo.getNome()) &&
				nomeJaCadastrado(jogoUpdateDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		modelMapper.map(jogoUpdateDTO, jogo);
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public void deleteById(Long id) {
		validateId(id);
		jogoRepository.deleteById(id);
	}
	
	//TipoDispositivo
	public JogoDTO addTipoDispositivo(Long jogoId, Long tipoDispositivoId) {
		Jogo jogo = validateId(jogoId);
		TipoDispositivoDTO tipoDispositivoDTO = tipoDispositivoService.findById(tipoDispositivoId);

		jogo.getTipoDispositivoList().add(tipoDispositivoService.dtoTotipoDispositivo(tipoDispositivoDTO));
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public JogoDTO removeTipoDispositivo(Long jogoId, Long tipoDispositivoId) {
		Jogo jogo = validateId(jogoId);
		tipoDispositivoService.validateId(tipoDispositivoId);
		
		if (!jogo.getTipoDispositivoList().removeIf(tipoDispositivo -> tipoDispositivo.getId().equals(tipoDispositivoId)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_TIPO_NAO_ENCONTRADO);

		return jogoToJogoDTO(jogoRepository.save(jogo));
	}

	//Auxiliary methods
	private boolean nomeJaCadastrado(String nome) {
		return jogoRepository.findByNome(nome).isPresent();
	}
	
	private Jogo validateId(Long id) {
		return jogoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}
	
	private JogoDTO jogoToJogoDTO(Jogo jogo) {
		return modelMapper.map(jogo, JogoDTO.class);
	}
	
	private Jogo JogoDTOToJogo(JogoDTO jogoDTO) {
		return modelMapper.map(jogoDTO, Jogo.class);
	}

}
