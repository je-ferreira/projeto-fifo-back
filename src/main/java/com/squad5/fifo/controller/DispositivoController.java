package com.squad5.fifo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad5.fifo.service.DispositivoService;

@RestController @RequestMapping("/dispositivos")
public class DispositivoController {

	@Autowired
	private DispositivoService service;
}
