package com.squad5.fifo.model;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class HisMetodo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//Chave Primaria gerada automaticamente
	private Long id;
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@JoinColumn(name = "tipoMetodo", foreignKey = @ForeignKey(name = "tipoMetodo_id")) // Chave Estrangeira
	@ManyToOne
	private TipoMetodo tipoMetodo;

}
