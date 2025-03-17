package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
