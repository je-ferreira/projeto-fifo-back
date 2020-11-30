package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.squad5.fifo.infra.NullOrNotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data @EqualsAndHashCode(callSuper = true)
public class JogoInsertDTO extends JogoDTO {

	@JsonIgnore
	private Long id;

	@NotBlank
	private String nome;
	
	@NotNull
	private Boolean ativo;

	@NullOrNotBlank
	private String urlsCapa;

	@JsonIgnore
	private Long dispositivoPreferencial;

	@JsonIgnore
	private List<Long> tipoDispositivoIdList;

}
