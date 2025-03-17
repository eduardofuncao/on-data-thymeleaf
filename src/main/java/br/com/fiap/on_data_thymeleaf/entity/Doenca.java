package br.com.fiap.on_data_thymeleaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Doenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    private double taxaReincidencia;

    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;

    @OneToMany(mappedBy = "doenca")
    private List<Ocorrencia> ocorrencias;

    public Doenca() {
    }

    public Doenca(long id, String nome, double taxaReincidencia, Gravidade gravidade, List<Ocorrencia> ocorrencias) {
        this.id = id;
        this.nome = nome;
        this.taxaReincidencia = taxaReincidencia;
        this.gravidade = gravidade;
        this.ocorrencias = ocorrencias;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome não pode ser vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome não pode ser vazio") String nome) {
        this.nome = nome;
    }

    public double getTaxaReincidencia() {
        return taxaReincidencia;
    }

    public void setTaxaReincidencia(double taxaReincidencia) {
        this.taxaReincidencia = taxaReincidencia;
    }

    public Gravidade getGravidade() {
        return gravidade;
    }

    public void setGravidade(Gravidade gravidade) {
        this.gravidade = gravidade;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
}
