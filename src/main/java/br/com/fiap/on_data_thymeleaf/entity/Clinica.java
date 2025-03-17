package br.com.fiap.on_data_thymeleaf.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;

    @NotBlank(message = "Cidade não pode ser vazio")
    private String cidade;

    private double custoMedioConsulta;

    @OneToMany(mappedBy = "clinica")
    private List<Dentista> destistas;

    public Clinica() {
    }

    public Clinica(long id, String nome, String endereco, String cidade, double custoMedioConsulta, List<Dentista> destistas) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.custoMedioConsulta = custoMedioConsulta;
        this.destistas = destistas;
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

    public List<Dentista> getDestistas() {
        return destistas;
    }

    public void setDestistas(List<Dentista> destistas) {
        this.destistas = destistas;
    }
}
