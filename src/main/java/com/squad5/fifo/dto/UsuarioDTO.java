package com.squad5.fifo.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private Boolean ativo;

    private Long cargoUsuario;

    private Long node;

}
