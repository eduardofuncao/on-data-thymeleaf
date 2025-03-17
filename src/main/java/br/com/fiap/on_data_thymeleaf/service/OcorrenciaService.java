package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.OcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.controller.dto.PacienteDTO;
import br.com.fiap.on_data_thymeleaf.entity.Dentista;
import br.com.fiap.on_data_thymeleaf.entity.Doenca;
import br.com.fiap.on_data_thymeleaf.entity.Ocorrencia;
import br.com.fiap.on_data_thymeleaf.entity.Paciente;
import br.com.fiap.on_data_thymeleaf.exception.DataFuturaException;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.exception.OcorrenciaJaAprovadaException;
import br.com.fiap.on_data_thymeleaf.repository.DentistaRepository;
import br.com.fiap.on_data_thymeleaf.repository.DoencaRepository;
import br.com.fiap.on_data_thymeleaf.repository.OcorrenciaRepository;
import br.com.fiap.on_data_thymeleaf.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final PacienteRepository pacienteRepository;
    private final DoencaRepository doencaRepository;
    private final DentistaRepository dentistaRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, PacienteRepository pacienteRepository,
                             DoencaRepository doencaRepository, DentistaRepository dentistaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.pacienteRepository = pacienteRepository;
        this.doencaRepository = doencaRepository;
        this.dentistaRepository = dentistaRepository;
    }

    public OcorrenciaDTO criarOcorrencia(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = convertToEntity(ocorrenciaDTO);

        //checa se data da ocorrência não é futura
        if (ocorrencia.getData() != null) {
            if (ocorrencia.getData().isAfter(LocalDateTime.now().plusDays(1))){
                throw new DataFuturaException("Não é possível criar uma Ocorrência com data futura");
            }
        }

        List<Paciente> pacientes = pacienteRepository.findAll();
        Paciente paciente = pacienteRepository.findById(ocorrenciaDTO.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado id" + ocorrenciaDTO.getPacienteId()));

        Doenca doenca = doencaRepository.findById(ocorrenciaDTO.getDoencaId())
                        .orElseThrow(() -> new RuntimeException("Doença não encontrada"));

        Dentista dentista = dentistaRepository.findById(ocorrenciaDTO.getDentistaId())
                        .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));

        ocorrencia.setPaciente(paciente);
        ocorrencia.setDoenca(doenca);
        ocorrencia.setDentista(dentista);

        Ocorrencia savedOcorrencia = ocorrenciaRepository.save(ocorrencia);
        ocorrenciaDTO.setId(savedOcorrencia.getId());
        return ocorrenciaDTO;
    }

    public List<OcorrenciaDTO> listarOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();

        return ocorrencias.stream().map(ocorrencia -> {
            OcorrenciaDTO returnedOcorrenciaDTO = convertToDTO(ocorrencia);
            returnedOcorrenciaDTO.setDentistaId(ocorrencia.getDentista().getId());
            returnedOcorrenciaDTO.setDentistaNome(ocorrencia.getDentista().getNome());
            returnedOcorrenciaDTO.setDoencaId(ocorrencia.getDoenca().getId());
            returnedOcorrenciaDTO.setDoencaNome(ocorrencia.getDoenca().getNome());
            returnedOcorrenciaDTO.setPacienteId(ocorrencia.getPaciente().getId());
            returnedOcorrenciaDTO.setPacienteNome(ocorrencia.getPaciente().getNome());
            return returnedOcorrenciaDTO;
        }).toList();
    }

    public List<OcorrenciaDTO> listarOcorrenciasAprovadas() {
        return ocorrenciaRepository.findByAprovadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OcorrenciaDTO> listarOcorrenciasReprovadas() {
        return ocorrenciaRepository.findByAprovadoFalse()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OcorrenciaDTO buscarOcorrenciaPorId(Long id) {
        Ocorrencia foundOcorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ocorrência não encontrada"));
        return convertToDTO(foundOcorrencia);
    }

    public void deletarOcorrencia(Long id) {ocorrenciaRepository.deleteById(id);}

    public OcorrenciaDTO aprovarOcorrencia(Long id) {
        Ocorrencia foundOcorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ocorrencia não encontrada"));
        if(foundOcorrencia.isAprovado()) {
            throw new OcorrenciaJaAprovadaException("A ocorrência já está aprovada");
        }
        foundOcorrencia.setAprovado(true);
        return convertToDTO(ocorrenciaRepository.save(foundOcorrencia));
    }

    public Map<Integer, Double> mapearValorPorMes() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return ocorrencias.stream()
                .collect(Collectors.groupingBy(
                        ocorrencia -> ocorrencia.getData().getMonthValue(),
                        Collectors.summingDouble(ocorrencia ->
                                ocorrencia.getData().getYear()==LocalDateTime.now().getYear()?ocorrencia.getValor():0))
                );
    }

    private OcorrenciaDTO convertToDTO(Ocorrencia ocorrencia) {
        OcorrenciaDTO ocorrenciaDTO = new OcorrenciaDTO();
        ocorrenciaDTO.setId(ocorrencia.getId());
        ocorrenciaDTO.setData(ocorrencia.getData());
        ocorrenciaDTO.setCodigoOcorrencia(ocorrencia.getCodigoOcorrencia());
        ocorrenciaDTO.setAprovada(ocorrencia.isAprovado());
        ocorrenciaDTO.setValor(ocorrencia.getValor());
        ocorrenciaDTO.setDuracaoHoras(ocorrencia.getDuracaoHoras());


        return ocorrenciaDTO;
    }

    private Ocorrencia convertToEntity(OcorrenciaDTO ocorrenciaDTO) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setId(ocorrenciaDTO.getId());
        ocorrencia.setData(ocorrenciaDTO.getData());
        ocorrencia.setCodigoOcorrencia(ocorrenciaDTO.getCodigoOcorrencia());
        ocorrencia.setValor(ocorrenciaDTO.getValor());
        ocorrencia.setDuracaoHoras(ocorrenciaDTO.getDuracaoHoras());

        return ocorrencia;
    }
}
