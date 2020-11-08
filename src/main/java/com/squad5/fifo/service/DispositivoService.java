package com.squad5.fifo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad5.fifo.dto.DispositivoDTO;
import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	private DispositivoRepository repository;
	
	public DispositivoDTO findById(long id) {
		return null;
	}
	
	public List<DispositivoDTO> findAll() {
		return new ArrayList<DispositivoDTO>();
	}
	
	public DispositivoDTO insert(DispositivoDTO dto) {
		return dto;
	}
	
	public DispositivoDTO update(DispositivoDTO dto) {
		return dto;
	}
	
	public void deleteById(long id) {
		return;
	}
}
