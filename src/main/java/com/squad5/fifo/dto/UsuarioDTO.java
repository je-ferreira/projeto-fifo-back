package com.squad5.fifo.dto;

import com.squad5.fifo.model.CargoUsuario;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private Boolean ativo;

    private CargoUsuario cargoUsuario;

    private Long vez;

}
