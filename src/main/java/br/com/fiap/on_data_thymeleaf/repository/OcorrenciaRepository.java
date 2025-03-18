package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findByAprovadoTrue();

    List<Ocorrencia> findByAprovadoFalse();

    Boolean existsByPacienteId(Long pacienteId);
}
