package com.squad5.fifo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable @Data
@Builder @AllArgsConstructor
@NoArgsConstructor
public class ParticipacaoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long vez;

    private Long usuario;

}
