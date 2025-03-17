package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.entity.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {
}
