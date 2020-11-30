package com.squad5.fifo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class ConfirmacaoDTO {

    private String nomeDispositivo;

    private String nomeJogo;

    private List<String> nomeConvidadoList;

}
