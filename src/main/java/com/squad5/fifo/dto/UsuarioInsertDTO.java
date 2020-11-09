package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UsuarioInsertDTO extends UsuarioDTO{

    @JsonIgnore
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

}
