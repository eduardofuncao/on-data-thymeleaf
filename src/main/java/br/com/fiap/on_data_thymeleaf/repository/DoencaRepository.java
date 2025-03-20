package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.controller.dto.DoencaOcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.entity.Doenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoencaRepository extends JpaRepository<Doenca, Long> {

    @Query(value =
            "DECLARE " +
                    "  v_cursor SYS_REFCURSOR; " +
                    "BEGIN " +
                    "  pkg_doenca_ocorrencias.listar_doencas_ocorrencias_custo(v_cursor); " +
                    "  ?:=v_cursor; " +
                    "END;",
            nativeQuery = true)
    List<DoencaOcorrenciaDTO> listarDoencasOcorrenciasCusto();
}
