package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ConviteInsertDTO {

    @NotNull
    private Long jogo;

    @NotNull
    private Long convidante;

    private Long dispositivo;

    @NotNull
    private List<Long> convidadoList;

}
