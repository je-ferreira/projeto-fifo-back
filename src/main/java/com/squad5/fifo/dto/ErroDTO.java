package com.squad5.fifo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {

    private String msg;

    private String cause;

}
