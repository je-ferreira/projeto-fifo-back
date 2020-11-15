package com.squad5.fifo.service;

import java.util.List;
import java.util.stream.Collectors;

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
	private static final String MSG_NOME_VAZIO = "Nome vazio.";

	//Dependencies
	private final JogoRepository jogoRepository;
	private final ModelMapper modelMapper;
	
	//CRUD
	public JogoDTO findById(Long id) {
		return jogoToDTO(validateId(id));
	}
	
	public List<JogoDTO> findAll() {
		return jogoRepository.findAll().stream()
				.map(j -> jogoToDTO(j))
				.collect(Collectors.toList());				
	}
	
	public JogoDTO insert(JogoInsertDTO insertDTO) {
		if (insertDTO.getNome() == null || insertDTO.getNome().isBlank())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_VAZIO);
		if (exists(insertDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);
		
		Jogo jogo = jogoRepository.save(dtoToJogo(insertDTO));
		return jogoToDTO(jogo);
	}
	
	public JogoDTO update(JogoUpdateDTO updateDTO) {
		Jogo jogo = validateId(updateDTO.getId());
		
		String newNome = updateDTO.getNome();
		if (newNome != null)
		{
			if (newNome.isBlank())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_VAZIO);		
			if (!newNome.equals(jogo.getNome()) && exists(newNome))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);
		}			
		
		jogo = jogoRepository.save(dtoToJogo(updateDTO));
		return jogoToDTO(jogo);
	}
	
	public void deleteById(Long id) {
		validateId(id);
		jogoRepository.deleteById(id);
	}
	
	//Auxiliary methods
	private boolean exists(String nome) {
		return jogoRepository.findByNome(nome).isPresent();
	}
	
	private Jogo validateId(Long id) {
		return jogoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}
	
	private JogoDTO jogoToDTO(Jogo jogo) {
		return modelMapper.map(jogo, JogoDTO.class);
	}
	
	private Jogo dtoToJogo(JogoDTO jogoDTO) {
		return modelMapper.map(jogoDTO, Jogo.class);
	}
}
