package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UsuarioUpdateDTO extends UsuarioDTO{

    @NotNull
    private Long id;

}
