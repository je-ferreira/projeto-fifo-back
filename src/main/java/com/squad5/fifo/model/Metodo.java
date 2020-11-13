package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Metodo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Chave Primaria gerada automaticamente
	private long id;
	String estado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoMetodo getTipoMetodo() {
		return tipoMetodo;
	}

	public void setTipoMetodo(TipoMetodo tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}

	@JoinColumn(name = "tipoMetodo", foreignKey = @ForeignKey(name = "Tipometodo_id")) // Chave Estrangeira
	@ManyToOne
	private TipoMetodo tipoMetodo;

}
