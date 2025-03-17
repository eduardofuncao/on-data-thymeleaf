package br.com.fiap.on_data_thymeleaf.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class DentistaDTO {

    private long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    private int idade;

    @NotBlank(message = "Registro não pode ser vazio")
    private String registro;

    private double salario;

    private long clinicaId;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public @NotBlank(message = "Registro não pode ser vazio") String getRegistro() {
        return registro;
    }

    public void setRegistro(@NotBlank(message = "Registro não pode ser vazio") String registro) {
        this.registro = registro;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(long clinicaId) {
        this.clinicaId = clinicaId;
    }
}
