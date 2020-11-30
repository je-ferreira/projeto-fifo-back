package com.squad5.fifo.infra;

import com.squad5.fifo.dto.ErroDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j @RestControllerAdvice
public class ExceptionHandlerAdvice {

    public static final String ERRO_INESPERADO = "Ocorreu um erro inesperado pelo sistema.";
    public static final String BAD_REQUEST = "Uma request problemática foi identificada.";

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErroDTO> handleException(Throwable e){
        log.error(ERRO_INESPERADO, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErroDTO(
                ERRO_INESPERADO,
                e.getMessage()
        ));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroDTO> handleResponseStatusException(Exception e){
        log.debug(BAD_REQUEST, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO(
                e.getMessage(),
                e.getCause() != null ? e.getCause().getMessage() : null
        ));
    }

}
