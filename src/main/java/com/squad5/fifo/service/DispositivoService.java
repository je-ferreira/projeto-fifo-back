package com.squad5.fifo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.squad5.fifo.dto.DispositivoInsertDTO;
import com.squad5.fifo.dto.DispositivoUpdateDTO;
import com.squad5.fifo.dto.TipoDispositivoDTO;
import com.squad5.fifo.model.TipoDispositivo;
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
	private static final String MSG_NOME_JA_CADASTRADO = "Já há um dispositivo com o nome fornecido.";
	private static final String MSG_ID_TIPO_NAO_ENCONTRADO = "Não há nenhum tipo de dispositivo com esse id vinculado ao dispositivo.";
	private static final String MSG_TIPO_JA_CADASTRADO = "O tipo de dispositivo informado já está relacionado ao dispositivo em questão.";

	private final DispositivoRepository dispositivoRepository;

	private final ModelMapper modelMapper;

	private final NodeService nodeService;

	private final TipoDispositivoService tipoDispositivoService;

	public DispositivoDTO findById(Long id) {
		return dispositivoToDispositivoDTO(findModelById(id));
	}

	public List<DispositivoDTO> findAll() {
		return dispositivoRepository.findAll().stream()
				.map(this::dispositivoToDispositivoDTO)
				.collect(Collectors.toList());
	}

	public DispositivoDTO insert(DispositivoInsertDTO dispositivoInsertDTO) {
		if(dispositivoRepository.findByNome(dispositivoInsertDTO.getNome()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		Dispositivo dispositivo = dispositivoDTOToDispositivo(dispositivoInsertDTO);
		dispositivo.setTipoDispositivoList(new ArrayList<>());
		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	public DispositivoDTO update(DispositivoUpdateDTO dispositivoUpdateDTO) {
		Dispositivo dispositivo = findModelById(dispositivoUpdateDTO.getId());

		if(dispositivoUpdateDTO.getNome() != null &&
				!dispositivoUpdateDTO.getNome().equals(dispositivo.getNome()) &&
				dispositivoRepository.findByNome(dispositivoUpdateDTO.getNome()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_NOME_JA_CADASTRADO);

		modelMapper.map(dispositivoUpdateDTO, dispositivo);
		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	public void deleteById(Long id) {
		findModelById(id);
		dispositivoRepository.deleteById(id);
	}

	public DispositivoDTO addTipoDispositivo(Long dispositivoId, Long tipoDispositivoId) {
		Dispositivo dispositivo = findModelById(dispositivoId);
		TipoDispositivoDTO tipoDispositivoDTO = tipoDispositivoService.findById(tipoDispositivoId);

		if(dispositivo.getTipoDispositivoList().stream().anyMatch(tipoDispositivo -> tipoDispositivo.getId().equals(tipoDispositivoId)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_TIPO_JA_CADASTRADO);

		dispositivo.getTipoDispositivoList().add(tipoDispositivoService.dtoTotipoDispositivo(tipoDispositivoDTO));
		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	public DispositivoDTO removeTipoDispositivo(Long dispositivoId, Long tipoDispositivoId) {
		Dispositivo dispositivo = findModelById(dispositivoId);
		tipoDispositivoService.findModelById(tipoDispositivoId);

		if (!dispositivo.getTipoDispositivoList().removeIf(tipoDispositivo -> tipoDispositivo.getId().equals(tipoDispositivoId)))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_TIPO_NAO_ENCONTRADO);

		return dispositivoToDispositivoDTO(dispositivoRepository.save(dispositivo));
	}

	Dispositivo findModelById(Long id) {
		return dispositivoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, MSG_ID_NAO_ENCONTRADO)
		);
	}

	DispositivoDTO dispositivoToDispositivoDTO(Dispositivo dispositivo){
		DispositivoDTO dispositivoDTO = modelMapper.map(dispositivo, DispositivoDTO.class);
		dispositivoDTO.setAtualId(dispositivo.getAtual() == null ? null : dispositivo.getAtual().getId());
		dispositivoDTO.setFilaId(dispositivo.getFila() == null ? null : dispositivo.getFila().getId());
		dispositivo.getTipoDispositivoList().stream()
				.map(TipoDispositivo::getId)
				.forEach(dispositivoDTO.getTipoDispositivoIdList()::add);

		return dispositivoDTO;
	}

	Dispositivo dispositivoDTOToDispositivo(DispositivoDTO dispositivoDTO){
		Dispositivo dispositivo = modelMapper.map(dispositivoDTO, Dispositivo.class);
		if(dispositivoDTO.getAtualId() != null)
			dispositivo.setAtual(nodeService.findModelById(dispositivoDTO.getAtualId()));
		if(dispositivoDTO.getFilaId() != null)
			dispositivo.setFila(nodeService.findModelById(dispositivoDTO.getFilaId()));
		if(dispositivoDTO.getTipoDispositivoIdList() != null)
			dispositivo.setTipoDispositivoList(dispositivoDTO.getTipoDispositivoIdList().stream()
					.map(tipoDispositivoService::findModelById)
					.collect(Collectors.toList()));

		return dispositivo;
	}

}
