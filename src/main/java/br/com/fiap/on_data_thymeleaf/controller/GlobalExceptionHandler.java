package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.controller.dto.ErroDetalhesDTO;
import br.com.fiap.on_data_thymeleaf.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataFuturaException.class)
    public ModelAndView handleDataFuturaException(DataFuturaException ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI(),
                "Data futura inválida"
        );
    }

    @ExceptionHandler(DentistaDuplicadoException.class)
    public ModelAndView handleDentistaDuplicadoException(DentistaDuplicadoException ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI(),
                "Dentista duplicado"
        );
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ModelAndView handleNaoEncontradoException(NaoEncontradoException ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getRequestURI(),
                "Recurso não encontrado"
        );
    }

    @ExceptionHandler(OcorrenciaJaAprovadaException.class)
    public ModelAndView handleOcorrenciaJaAprovadaException(OcorrenciaJaAprovadaException ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI(),
                "Ocorrência já aprovada"
        );
    }

    @ExceptionHandler(PacienteComOcorrenciaException.class)
    public ModelAndView handlePacienteComOcorrenciaException(PacienteComOcorrenciaException ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI(),
                "Paciente com ocorrências associadas não pode ser excluído"
        );
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        return buildErrorModelAndView(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request.getRequestURI(),
                "Erro interno no servidor"
        );
    }

    private ModelAndView buildErrorModelAndView(HttpStatus status, String message, String path, String title) {
        ModelAndView modelAndView = new ModelAndView("error/custom-error");
        modelAndView.addObject("timestamp", LocalDateTime.now());
        modelAndView.addObject("status", status.value());
        modelAndView.addObject("error", status.getReasonPhrase());
        modelAndView.addObject("message", message);
        modelAndView.addObject("path", path);
        modelAndView.addObject("title", title);
        modelAndView.setStatus(status);

        return modelAndView;
    }


}
