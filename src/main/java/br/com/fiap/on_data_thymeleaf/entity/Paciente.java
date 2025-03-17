package br.com.fiap.on_data_thymeleaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Enumerated(EnumType.STRING)
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

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    
    @OneToMany(mappedBy = "paciente")
    private List<Ocorrencia> ocorrencias;

    public Paciente() {
    }

    public Paciente(Long id, Genero genero, String nome, int idade, String endereco, String cidade, boolean fumante, double renda, double visitasPorAno, Categoria categoria, List<Ocorrencia> ocorrencias) {
        this.id = id;
        this.genero = genero;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.cidade = cidade;
        this.fumante = fumante;
        this.renda = renda;
        this.visitasPorAno = visitasPorAno;
        this.categoria = categoria;
        this.ocorrencias = ocorrencias;
    }

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

    public @NotBlank(message = "Endereço não pode ser vazio") String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotBlank(message = "Endereço não pode ser vazio") String endereco) {
        this.endereco = endereco;
    }

    public @NotBlank(message = "Cidade não pode ser vazia") String getCidade() {
        return cidade;
    }

    public void setCidade(@NotBlank(message = "Cidade não pode ser vazia") String cidade) {
        this.cidade = cidade;
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

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
}
