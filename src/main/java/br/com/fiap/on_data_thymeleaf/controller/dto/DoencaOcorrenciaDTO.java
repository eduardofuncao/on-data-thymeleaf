package br.com.fiap.on_data_thymeleaf.controller.dto;

import java.math.BigDecimal;

public class DoencaOcorrenciaDTO {
    private Long idDoenca;
    private String nomeDoenca;
    private Long totalOcorrencias;
    private BigDecimal totalGasto;

    public DoencaOcorrenciaDTO(Long idDoenca,String nomeDoenca, Long totalOcorrencias, BigDecimal totalGasto) {
        this.idDoenca = idDoenca;
        this.nomeDoenca = nomeDoenca;
        this.totalOcorrencias = totalOcorrencias;
        this.totalGasto = totalGasto;
    }

    public Long getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(Long idDoenca) {
        this.idDoenca = idDoenca;
    }

    public String getNomeDoenca() {
        return nomeDoenca;
    }

    public void setNomeDoenca(String nomeDoenca) {
        this.nomeDoenca = nomeDoenca;
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
