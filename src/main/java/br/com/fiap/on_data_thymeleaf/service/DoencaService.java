package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.DoencaDTO;
import br.com.fiap.on_data_thymeleaf.controller.dto.DoencaOcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.entity.Doenca;
import br.com.fiap.on_data_thymeleaf.entity.Gravidade;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.repository.DoencaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoencaService {

    @Autowired
    private DoencaRepository doencaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public DoencaDTO criarDoenca(DoencaDTO doencaDTO) {
        Doenca savedDoenca = doencaRepository.save(convertToEntity(doencaDTO));
        doencaDTO.setId(savedDoenca.getId());
        return doencaDTO;
    }

    public Page<DoencaDTO> listarDoencas(Pageable pageable) {
        return doencaRepository.findAll(pageable).map(this::convertToDTO);
    }

    public List<DoencaDTO> listarDoencas() {
        return doencaRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public DoencaDTO buscarDoencaPorId(Long id) {
        Doenca foundDoenca = doencaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Doença não encontrada"));
        return convertToDTO(foundDoenca);
    }

    public void deletarDoenca(Long id) {doencaRepository.deleteById(id);}

    // chama procedure do banco de dados oracle -> requisito entrega DB
    public List<DoencaOcorrenciaDTO> agruparOcorrenciasPorDoenca() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("pkg_doenca_ocorrencias.listar_doencas_ocorrencias_custo");
        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> results = (List<Object[]>) query.getResultList();

        List<DoencaOcorrenciaDTO> dtoList = new ArrayList<>();
        for (Object[] result : results) {
            dtoList.add(new DoencaOcorrenciaDTO(
                    ((Number) result[0]).longValue(),
                    (String) result[1].toString(),
                    ((Number) result[2]).longValue(),
                    (BigDecimal) result[3]
            ));
        }

        return dtoList;
    }

    private DoencaDTO convertToDTO(Doenca doenca) {
        DoencaDTO doencaDTO = new DoencaDTO();
        doencaDTO.setId(doenca.getId());
        doencaDTO.setNome(doenca.getNome());
        doencaDTO.setTaxaReincidencia(doenca.getTaxaReincidencia());
        doencaDTO.setGravidade(doenca.getGravidade());

        return doencaDTO;
    }

    private Doenca convertToEntity(DoencaDTO doencaDTO) {
        Doenca doenca = new Doenca();
        doenca.setId(doencaDTO.getId());
        doenca.setNome(doencaDTO.getNome());
        doenca.setTaxaReincidencia(doencaDTO.getTaxaReincidencia());
        doenca.setGravidade(doencaDTO.getGravidade());

        return doenca;
    }
}
