package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
public class TipoDispositivo {
	
	@Id @GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String nome;

}
