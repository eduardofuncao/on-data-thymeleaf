package br.com.fiap.on_data_thymeleaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    private int idade;

    @NotBlank(message = "Registro não pode ser vazio")
    private String registro;

    private double salario;

    @ManyToOne
    private Clinica clinica;

    @OneToMany(mappedBy = "dentista")
    private List<Ocorrencia> ocorrencias;

    public Dentista() {
    }

    public Dentista(long id, String nome, int idade, String registro, Clinica clinica, double salario, List<Ocorrencia> ocorrencias) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.registro = registro;
        this.clinica = clinica;
        this.salario = salario;
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

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
}
