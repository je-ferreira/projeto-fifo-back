package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.squad5.fifo.infra.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DispositivoUpdateDTO extends DispositivoDTO {

    @NotNull
    private Long id;

    @NullOrNotBlank
    private String nome;

    @JsonIgnore
    private List<Long> tipoDispositivoIdList;

}
