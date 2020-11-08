package com.squad5.fifo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad5.fifo.model.Dispositivo;
import com.squad5.fifo.repository.DispositivoRepository;

@Service
public class DispositivoService {
	
	@Autowired
	private DispositivoRepository repository;
}
