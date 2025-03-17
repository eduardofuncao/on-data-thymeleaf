package br.com.fiap.on_data_thymeleaf.controller.dto;

import br.com.fiap.on_data_thymeleaf.entity.Categoria;
import br.com.fiap.on_data_thymeleaf.entity.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class PacienteDTO {
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    private Genero genero;

    private int idade;

    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotBlank(message = "Cidade não pode ser vazia")
    private String cidade;

    private boolean fumante;

    @Positive(message = "Valor de renda deve ser positivo")
    private double renda;

    private double visitasPorAno;

    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nome não pode ser vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Nome não pode ser vazio") String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public @NotBlank(message = "Cidade não pode ser vazia") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank(message = "Cidade não pode ser vazia") String cidade) {
        this.cidade = cidade;
    }

    public @NotBlank(message = "Endereço não pode ser vazio") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank(message = "Endereço não pode ser vazio") String endereco) {
        this.endereco = endereco;
    }

    public boolean isFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    @Positive(message = "Valor de renda deve ser positivo")
    public double getRenda() {
        return renda;
    }

    public void setRenda(@Positive(message = "Valor de renda deve ser positivo") double renda) {
        this.renda = renda;
    }

    public double getVisitasPorAno() {
        return visitasPorAno;
    }

    public void setVisitasPorAno(double visitasPorAno) {
        this.visitasPorAno = visitasPorAno;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
