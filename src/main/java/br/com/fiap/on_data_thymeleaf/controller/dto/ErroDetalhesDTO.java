package br.com.fiap.on_data_thymeleaf.controller.dto;

import java.time.LocalDateTime;

public class ErroDetalhesDTO {
    private LocalDateTime timestamp;
    private String mensagem;
    private String detalhes;

    public ErroDetalhesDTO(LocalDateTime timestamp, String mensagem, String detalhes) {
        this.timestamp = timestamp;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    // Getters e Setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
