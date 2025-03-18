package br.com.fiap.on_data_thymeleaf.entity;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_ocorrencia")
    private LocalDateTime data;

    private String codigoOcorrencia;

    private double valor;

    private int duracaoHoras;

    private boolean aprovado;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Doenca doenca;

    @ManyToOne
    private Dentista dentista;

    public Ocorrencia() {
    }

    public Ocorrencia(long id, LocalDateTime data, String codigoOcorrencia, double valor, int duracaoHoras, Paciente paciente, boolean aprovado, Doenca doenca, Dentista dentista) {
        this.id = id;
        this.data = data;
        this.codigoOcorrencia = codigoOcorrencia;
        this.valor = valor;
        this.duracaoHoras = duracaoHoras;
        this.paciente = paciente;
        this.aprovado = aprovado;
        this.doenca = doenca;
        this.dentista = dentista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }
}
