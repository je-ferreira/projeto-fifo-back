package com.squad5.fifo.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class HomePartidaAtualDTO {

    private Long idVez;

    private Long idJogo;

    private String nomeJogo;

    private String nomeDispositivo;

    private Long idDispositivo;

}
