package com.squad5.fifo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DispositivoUpdateDTO extends DispositivoDTO {

    @NotNull
    private Long id;

}
