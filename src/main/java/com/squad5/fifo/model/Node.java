package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.engine.internal.ForeignKeys;

public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Chave Primaria gerada automaticamente
	private Long id;
	private String entrada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	@JoinColumn(name = "metodo", foreignKey = @ForeignKey(name = "metod_id")) // Chave Estrangeira
	@ManyToOne
	private Metodo metodo;

}
