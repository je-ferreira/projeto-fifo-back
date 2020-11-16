package com.squad5.fifo.dto;

import com.squad5.fifo.infra.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UsuarioUpdateDTO extends UsuarioDTO{

    @NotNull
    private Long id;

    @NullOrNotBlank
    private String email;

    @NullOrNotBlank
    private String nome;

}
