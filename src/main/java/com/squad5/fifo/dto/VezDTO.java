package com.squad5.fifo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VezDTO {

    private Long id;

    private Date entrada;

    private Date saida;

    private Long jogo;

    private Long dispositivo;

    private Long convidante;

    private List<Long> convidadoPendenteList;

}
