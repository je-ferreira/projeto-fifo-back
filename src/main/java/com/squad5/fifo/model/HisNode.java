package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class HisNode {

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

	public HisMetodo getHisMetodo() {
		return hisMetodo;
	}

	public void setHisMetodo(HisMetodo hisMetodo) {
		this.hisMetodo = hisMetodo;
	}

	@JoinColumn(name = "hisMetodo", foreignKey = @ForeignKey(name = "hisMetodo_id")) // Chave Estrangeira
	@ManyToOne
	private HisMetodo hisMetodo;

}
