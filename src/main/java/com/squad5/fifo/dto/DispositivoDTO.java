package com.squad5.fifo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DispositivoDTO{

    private Long id;

    private String nome;

    private Boolean ativo;

    private List<Long> tipoDispositivoIdList;

}
