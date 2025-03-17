package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.ClinicaDTO;
import br.com.fiap.on_data_thymeleaf.entity.Clinica;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.repository.ClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    public ClinicaDTO criarClinica(ClinicaDTO clinicaDTO) {
        Clinica savedClinica = clinicaRepository.save(convertToEntity(clinicaDTO));
        clinicaDTO.setId(savedClinica.getId());
        return clinicaDTO;
    }

    public Page<ClinicaDTO> listarClinicas(Pageable pageable) {
        return clinicaRepository.findAll(pageable).map(this::convertToDTO);
    }

    public ClinicaDTO buscarClinicaPorId(Long id) {
        Clinica foundClinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Clínica não encontrada"));
        return convertToDTO(foundClinica);
    }

    public void deletarClinica(Long id) {clinicaRepository.deleteById(id);}

    private ClinicaDTO convertToDTO(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setId(clinica.getId());
        clinicaDTO.setNome(clinica.getNome());
        clinicaDTO.setEndereco(clinica.getEndereco());
        clinicaDTO.setCidade(clinica.getCidade());
        clinicaDTO.setCustoMedioConsulta(clinica.getCustoMedioConsulta());

        return clinicaDTO;
    }

    private Clinica convertToEntity(ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setId(clinicaDTO.getId());
        clinica.setNome(clinicaDTO.getNome());
        clinica.setEndereco(clinicaDTO.getEndereco());
        clinica.setCidade(clinicaDTO.getCidade());
        clinica.setCustoMedioConsulta(clinicaDTO.getCustoMedioConsulta());

        return clinica;
    }
}
