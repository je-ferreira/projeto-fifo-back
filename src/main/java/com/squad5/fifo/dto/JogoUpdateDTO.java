package com.squad5.fifo.dto;

import javax.validation.constraints.NotNull;

import com.squad5.fifo.infra.NullOrNotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class JogoUpdateDTO extends JogoDTO {

	@NotNull
	private Long id;

	@NullOrNotBlank
	private String nome;

}
