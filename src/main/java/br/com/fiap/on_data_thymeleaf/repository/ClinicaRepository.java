package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
}
