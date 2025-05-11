package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.PacienteDTO;
import br.com.fiap.on_data_thymeleaf.controller.dto.PacienteGastoDTO;
import br.com.fiap.on_data_thymeleaf.entity.Paciente;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.exception.PacienteComOcorrenciaException;
import br.com.fiap.on_data_thymeleaf.repository.OcorrenciaRepository;
import br.com.fiap.on_data_thymeleaf.repository.PacienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PacienteService(PacienteRepository pacienteRepository, OcorrenciaRepository ocorrenciaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        Paciente savedPaciente = pacienteRepository.save(convertToEntity(pacienteDTO));
        pacienteDTO.setId(savedPaciente.getId());
        return pacienteDTO;
    }

    public Page<PacienteDTO> listarPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(this::convertToDTO);
    }

    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PacienteDTO buscarPacientePorId(Long id) {
        Paciente foundPaciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Paciente não encontrado id: " + id));
        return convertToDTO(foundPaciente);
    }

    public void deletarPaciente(Long id) {
        if (ocorrenciaRepository.existsByPacienteId(id)) {
            throw new PacienteComOcorrenciaException("Paciente com ocorrência associada não pode ser excluído");
        }
        pacienteRepository.deleteById(id);
    }

    // chama procedure do banco de dados oracle -> requisito entrega DB
    public List<PacienteGastoDTO> listarGastosPacientes() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("pkg_ocorrencias_pacientes.listar_gastos_pacientes");

        query.registerStoredProcedureParameter(1, void.class, ParameterMode.REF_CURSOR);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> results = (List<Object[]>) query.getResultList();

        List<PacienteGastoDTO> dtoList = new ArrayList<>();
        for (Object[] result : results) {

            dtoList.add(new PacienteGastoDTO(
                    ((Number) result[0]).longValue(),
                    (String) result[1],
                    ((Number) result[2]).longValue(),
                    (BigDecimal) result[3]
            ));
        }
        return dtoList;
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setNome(paciente.getNome());
        pacienteDTO.setGenero(paciente.getGenero());
        pacienteDTO.setIdade(paciente.getIdade());
        pacienteDTO.setEmail(paciente.getEmail());
        pacienteDTO.setEndereco(paciente.getEndereco());
        pacienteDTO.setCidade(paciente.getCidade());
        pacienteDTO.setFumante(paciente.isFumante());
        pacienteDTO.setRenda(paciente.getRenda());
        pacienteDTO.setVisitasPorAno(paciente.getVisitasPorAno());
        pacienteDTO.setCategoria(paciente.getCategoria());

        return pacienteDTO;
    }

    private Paciente convertToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        if(pacienteDTO.getId() != null) {paciente.setId(pacienteDTO.getId());}
        paciente.setNome(pacienteDTO.getNome());
        paciente.setGenero(pacienteDTO.getGenero());
        paciente.setIdade(pacienteDTO.getIdade());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setEndereco(pacienteDTO.getEndereco());
        paciente.setCidade(pacienteDTO.getCidade());
        paciente.setFumante(pacienteDTO.isFumante());
        paciente.setRenda(pacienteDTO.getRenda());
        paciente.setVisitasPorAno(pacienteDTO.getVisitasPorAno());
        paciente.setCategoria(pacienteDTO.getCategoria());

        return paciente;
    }
}
