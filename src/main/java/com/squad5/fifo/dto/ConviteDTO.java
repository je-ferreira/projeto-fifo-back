package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConviteDTO {

    @NotNull
    private Long convidante;

    @NotNull
    private Long usuario;

}
