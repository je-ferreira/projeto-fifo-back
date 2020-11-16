package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioInsertDTO extends UsuarioDTO{

    @JsonIgnore
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotNull
    private Boolean ativo;

    @NotNull
    private Long cargoUsuario;

}
