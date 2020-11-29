package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PartidaDTO {

    @NotNull
    private Long vez;

    @NotNull
    private List<ParticipacaoDTO> paticipacaoList;

}
