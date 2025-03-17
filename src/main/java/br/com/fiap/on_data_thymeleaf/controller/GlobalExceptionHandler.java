package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.controller.dto.ErroDetalhesDTO;
import br.com.fiap.on_data_thymeleaf.exception.DataFuturaException;
import br.com.fiap.on_data_thymeleaf.exception.DentistaDuplicadoException;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.exception.OcorrenciaJaAprovadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataFuturaException.class)
    public ResponseEntity<ErroDetalhesDTO> handleDataPassadaException(DataFuturaException ex, WebRequest request) {
        ErroDetalhesDTO erroDetalhes = new ErroDetalhesDTO(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DentistaDuplicadoException.class)
    public ResponseEntity<ErroDetalhesDTO> handleDentistaDuplicadoException(DentistaDuplicadoException ex, WebRequest request) {
        ErroDetalhesDTO erroDetalhes = new ErroDetalhesDTO(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroDetalhesDTO> handleNaoEncontradoException(NaoEncontradoException ex, WebRequest request) {
        ErroDetalhesDTO erroDetalhes = new ErroDetalhesDTO(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OcorrenciaJaAprovadaException.class)
    public ResponseEntity<ErroDetalhesDTO> handleOcorrenciaJaAprovadaException(OcorrenciaJaAprovadaException ex, WebRequest request) {
        ErroDetalhesDTO erroDetalhes = new ErroDetalhesDTO(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
    }

}
