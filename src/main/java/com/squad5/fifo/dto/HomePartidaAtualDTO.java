package com.squad5.fifo.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class HomePartidaAtualDTO {

    private Long idPartida;

    private Long idJogo;

    private String nomeJogo;

    private String nomeDispositivo;

}
