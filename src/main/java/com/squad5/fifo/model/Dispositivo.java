package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class Dispositivo {
	
	@Id @GeneratedValue
	private Long id;

	private String nome;

}
