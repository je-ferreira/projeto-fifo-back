package com.squad5.fifo.dto.autenticacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class JwtAuthenticationDto {
	@NotBlank
	@Email
	private String email ;
	private String senha ;
	
}
