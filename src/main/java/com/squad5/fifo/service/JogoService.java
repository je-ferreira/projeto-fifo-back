package com.squad5.fifo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.model.TipoDispositivo;
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

import javax.validation.constraints.NotNull;

@Service @RequiredArgsConstructor
public class JogoService {

	//Messages
	private static final String MSG_ID_NAO_ENCONTRADO = "Nenhum jogo com o id fornecido foi encontrado.";
	private static final String MSG_NOME_JA_CADASTRADO = "Já há um jogo com o nome fornecido.";
	private static final String MSG_ID_TIPO_NAO_ENCONTRADO = "Não há nenhum tipo de dispositivo com esse id vinculado ao jogo.";
	private static final String MSG_TIPO_JA_CADASTRADO = "O tipo de dispositivo informado já está relacionado ao jogo em questão.";
	private static final String MSG_DISPOSITIVO_INCOPATIVEL = "O dispositivo e o jogo são incompatíveis.";

	//Dependencies
	private final JogoRepository jogoRepository;
	private final TipoDispositivoService tipoDispositivoService;
	private final DispositivoService dispositivoService;
	private final ModelMapper modelMapper;
	
	//CRUD
	public JogoDTO findById(Long id) {
		return jogoToJogoDTO(findModelById(id));
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
		jogo.setTipoDispositivoList(new ArrayList<>());
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public JogoDTO update(JogoUpdateDTO jogoUpdateDTO) {
		Jogo jogo = findModelById(jogoUpdateDTO.getId());

		if(jogoUpdateDTO.getNome() != null
				&& !jogoUpdateDTO.getNome().equals(jogo.getNome())
				&& nomeJaCadastrado(jogoUpdateDTO.getNome()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		modelMapper.map(jogoUpdateDTO, jogo);
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public void deleteById(Long id) {
		findModelById(id);
		jogoRepository.deleteById(id);
	}
	
	//TipoDispositivo
	public JogoDTO addTipoDispositivo(@NotNull Long jogoId, @NotNull Long tipoDispositivoId) {
		Jogo jogo = findModelById(jogoId);
		TipoDispositivo tipoDispositivo = tipoDispositivoService.findModelById(tipoDispositivoId);

		if(jogo.getTipoDispositivoList().stream().anyMatch(tipo -> tipo.getId().equals(tipoDispositivoId)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_TIPO_JA_CADASTRADO);

		jogo.getTipoDispositivoList().add(tipoDispositivo);
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}
	
	public JogoDTO removeTipoDispositivo(@NotNull Long jogoId, @NotNull Long tipoDispositivoId) {
		Jogo jogo = findModelById(jogoId);
		tipoDispositivoService.findModelById(tipoDispositivoId);
		
		if (!jogo.getTipoDispositivoList().removeIf(tipoDispositivo -> tipoDispositivo.getId().equals(tipoDispositivoId)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_TIPO_NAO_ENCONTRADO);

		return jogoToJogoDTO(jogoRepository.save(jogo));
	}

	//Dispositivo
	public JogoDTO configurarDispositivoPreferencial(@NotNull Long jogoId, @NotNull Long dispositivoId){
		Jogo jogo = findModelById(jogoId);
		Dispositivo dispositivo = dispositivoService.findModelById(dispositivoId);
		if(!jogoDispositivoSaoCompativeis(jogo, dispositivo))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_DISPOSITIVO_INCOPATIVEL);

		jogo.setDispositivoPreferencial(dispositivo);
		return jogoToJogoDTO(jogoRepository.save(jogo));
	}

	//Auxiliary methods
	boolean nomeJaCadastrado(String nome) {
		return jogoRepository.findByNome(nome).isPresent();
	}
	
	Jogo findModelById(Long id) {
		return jogoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}
	
	JogoDTO jogoToJogoDTO(Jogo jogo) {
		JogoDTO jogoDTO = modelMapper.map(jogo, JogoDTO.class);
		jogoDTO.setTipoDispositivoIdList(new ArrayList<>());
		if(jogo.getDispositivoPreferencial() != null) jogoDTO.setDispositivoPreferencial(jogo.getDispositivoPreferencial().getId());
		jogo.getTipoDispositivoList().stream()
				.map(TipoDispositivo::getId)
				.forEach(jogoDTO.getTipoDispositivoIdList()::add);

		return jogoDTO;
	}
	
	Jogo JogoDTOToJogo(JogoDTO jogoDTO) {
		Jogo jogo = modelMapper.map(jogoDTO, Jogo.class);
		if(jogoDTO.getDispositivoPreferencial() != null) jogo.setDispositivoPreferencial(dispositivoService.findModelById(jogoDTO.getId()));
		if(jogoDTO.getTipoDispositivoIdList() != null)
			jogo.setTipoDispositivoList(jogoDTO.getTipoDispositivoIdList().stream()
					.map(tipoDispositivoService::findModelById)
					.collect(Collectors.toList()));

		return jogo;
	}

    List<Jogo> findAtivos() {
		return jogoRepository.findByAtivo(true);
    }

    Optional<Jogo> findAtualByDispositivo(Dispositivo dispositivo) {
		return jogoRepository.findAtualByDispositivo(dispositivo);
    }

    boolean jogoDispositivoSaoCompativeis(Jogo jogo, Dispositivo dispositivo){
		return dispositivo.getTipoDispositivoList().stream()
				.anyMatch(tipoDispositivo -> jogo.getTipoDispositivoList().stream()
						.anyMatch(tipoDispositivoJogo -> tipoDispositivo.getId().equals(tipoDispositivoJogo.getId())));
	}

}
