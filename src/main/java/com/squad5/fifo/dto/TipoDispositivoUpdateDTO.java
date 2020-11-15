package com.squad5.fifo.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class TipoDispositivoUpdateDTO extends TipoDispositivoDTO {

	@NotNull
	private Long id;
}
