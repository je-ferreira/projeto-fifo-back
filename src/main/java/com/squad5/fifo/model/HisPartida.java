package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 

public class HisPartida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Chave Primaria gerada automaticamente
	private long id;
	private String resultado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public HisNode getHisNode() {
		return hisNode;
	}

	public void setHisNode(HisNode hisNode) {
		this.hisNode = hisNode;
	}

	@JoinColumn(name = "hisNode", foreignKey = @ForeignKey(name = "hisNode_id")) // Chave Estrangeira
	@ManyToOne
	private HisNode hisNode;

}
