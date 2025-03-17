package br.com.fiap.on_data_thymeleaf.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ClinicaDTO {
    private long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotBlank(message = "Cidade não pode ser vazio")
    private String cidade;

    private double custoMedioConsulta;

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

    public @NotBlank(message = "Endereço não pode ser vazio") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank(message = "Endereço não pode ser vazio") String endereco) {
        this.endereco = endereco;
    }

    public @NotBlank(message = "Cidade não pode ser vazio") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank(message = "Cidade não pode ser vazio") String cidade) {
        this.cidade = cidade;
    }

    public double getCustoMedioConsulta() {
        return custoMedioConsulta;
    }

    public void setCustoMedioConsulta(double custoMedioConsulta) {
        this.custoMedioConsulta = custoMedioConsulta;
    }
}
