package br.com.fiap.on_data_thymeleaf.service;

import br.com.fiap.on_data_thymeleaf.controller.dto.DoencaDTO;
import br.com.fiap.on_data_thymeleaf.entity.Doenca;
import br.com.fiap.on_data_thymeleaf.entity.Gravidade;
import br.com.fiap.on_data_thymeleaf.exception.NaoEncontradoException;
import br.com.fiap.on_data_thymeleaf.repository.DoencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoencaService {

    @Autowired
    private DoencaRepository doencaRepository;

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
