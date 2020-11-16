package com.squad5.fifo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DispositivoDTO{

    private Long id;

    private String nome;

    private Boolean ativo;

    private Long filaId;

    private Long atualId;

    private List<Long> tipoDispositivoIdList;

}
