package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Chave Primaria gerada automaticamente
	private Long id;
	private String resultado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@JoinColumn(name = "node", foreignKey = @ForeignKey(name = "node_id")) //Chave Estrangeira
	@ManyToOne
	private Node node;

}
