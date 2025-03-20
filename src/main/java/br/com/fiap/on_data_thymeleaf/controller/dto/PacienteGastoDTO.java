package br.com.fiap.on_data_thymeleaf.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PacienteGastoDTO {
    private Long pacienteId;
    private String nomePaciente;
    private Long totalOcorrencias;
    private BigDecimal totalGasto;

    public PacienteGastoDTO(Long pacienteId, String nomePaciente, Long totalOcorrencias, BigDecimal totalGasto) {
        this.pacienteId = pacienteId;
        this.nomePaciente = nomePaciente;
        this.totalOcorrencias = totalOcorrencias;
        this.totalGasto = totalGasto;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Long getTotalOcorrencias() {
        return totalOcorrencias;
    }

    public void setTotalOcorrencias(Long totalOcorrencias) {
        this.totalOcorrencias = totalOcorrencias;
    }

    public BigDecimal getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(BigDecimal totalGasto) {
        this.totalGasto = totalGasto;
    }

}
