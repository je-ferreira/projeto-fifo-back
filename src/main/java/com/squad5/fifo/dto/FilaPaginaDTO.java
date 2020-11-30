package com.squad5.fifo.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FilaPaginaDTO {

    private String nomeDispositivo;

    private Long idJogo;

    private String nomeJogo;

}
