package br.com.fiap.on_data_thymeleaf.controller.dto;

import br.com.fiap.on_data_thymeleaf.entity.Gravidade;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class DoencaDTO {

    private long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    private double taxaReincidencia;

    private Gravidade gravidade;

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
}
