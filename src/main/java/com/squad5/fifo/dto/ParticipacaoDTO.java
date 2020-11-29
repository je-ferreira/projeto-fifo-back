package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squad5.fifo.model.ParticipacaoId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParticipacaoDTO {

    @JsonIgnore
    private ParticipacaoId id;

    @NotNull
    private Long usuario;

    @JsonIgnore
    private Long vez;

    @NotNull
    private Integer resultado;

}
