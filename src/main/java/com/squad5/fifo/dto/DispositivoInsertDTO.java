package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DispositivoInsertDTO extends DispositivoDTO {

    @JsonIgnore
    private Long id;

    @NotNull
    private String nome;

}
