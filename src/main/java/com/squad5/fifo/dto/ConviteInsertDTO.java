package com.squad5.fifo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConviteInsertDTO {

    private Long jogo;

    private Long convidante;

    private List<Long> convidadoList;

}
