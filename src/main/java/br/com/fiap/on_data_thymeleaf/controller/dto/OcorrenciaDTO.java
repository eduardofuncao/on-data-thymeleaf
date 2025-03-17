package br.com.fiap.on_data_thymeleaf.controller.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class OcorrenciaDTO extends RepresentationModel<OcorrenciaDTO> {
    private long id;
    private LocalDateTime data;

    private String codigoOcorrencia;

    private double valor;

    private int duracaoHoras;

    private boolean aprovada;

    private long pacienteId;
    private long doencaId;
    private long dentistaId;

    private String pacienteNome;
    private String doencaNome;
    private String dentistaNome;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoOcorrencia() {
        return codigoOcorrencia;
    }

    public void setCodigoOcorrencia(String codigoOcorrencia) {
        this.codigoOcorrencia = codigoOcorrencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(boolean aprovada) {
        this.aprovada = aprovada;
    }

    public long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public long getDoencaId() {
        return doencaId;
    }

    public void setDoencaId(long doencaId) {
        this.doencaId = doencaId;
    }

    public long getDentistaId() {
        return dentistaId;
    }

    public void setDentistaId(long dentistaId) {
        this.dentistaId = dentistaId;
    }

    public String getPacienteNome() {
        return pacienteNome;
    }

    public void setPacienteNome(String pacienteNome) {
        this.pacienteNome = pacienteNome;
    }

    public String getDoencaNome() {
        return doencaNome;
    }

    public void setDoencaNome(String doencaNome) {
        this.doencaNome = doencaNome;
    }

    public String getDentistaNome() {
        return dentistaNome;
    }

    public void setDentistaNome(String dentistaNome) {
        this.dentistaNome = dentistaNome;
    }
}
