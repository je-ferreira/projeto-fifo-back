package com.squad5.fifo.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squad5.fifo.infra.NullOrNotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data @EqualsAndHashCode(callSuper = true)
public class JogoUpdateDTO extends JogoDTO {

	@NotNull
	private Long id;

	@NullOrNotBlank
	private String nome;

	@NullOrNotBlank
	private String urlsCapa;

	@JsonIgnore
	private Long dispositivoPreferencial;

	@JsonIgnore
	private List<Long> tipoDispositivoIdList;

}
