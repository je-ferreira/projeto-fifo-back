package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class JogoInsertDTO extends JogoDTO {

	@JsonIgnore
	private Long id;
}
