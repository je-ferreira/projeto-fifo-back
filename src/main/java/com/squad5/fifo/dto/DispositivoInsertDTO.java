package com.squad5.fifo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DispositivoInsertDTO extends DispositivoDTO {

    @JsonIgnore
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Boolean ativo;

    @JsonIgnore
    private List<Long> tipoDispositivoIdList;

}
