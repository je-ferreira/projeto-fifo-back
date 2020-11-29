package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConviteAceitoDTO {

    @NotNull
    private Long vez;

    @NotNull
    private Long usuario;

}
