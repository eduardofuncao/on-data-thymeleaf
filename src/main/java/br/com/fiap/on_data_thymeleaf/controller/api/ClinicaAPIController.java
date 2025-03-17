package br.com.fiap.on_data_thymeleaf.controller.api;

import br.com.fiap.on_data_thymeleaf.controller.dto.ClinicaDTO;
import br.com.fiap.on_data_thymeleaf.repository.ClinicaRepository;
import br.com.fiap.on_data_thymeleaf.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clinicas")
public class ClinicaAPIController {
    @Autowired
    private ClinicaService clinicaService;
    @Autowired
    private ClinicaRepository clinicaRepository;

    @PostMapping
    ResponseEntity<ClinicaDTO> criarClinica(@RequestBody ClinicaDTO clinicaDTO){
        return ResponseEntity.ok(clinicaService.criarClinica(clinicaDTO));
    }

    @GetMapping
    ResponseEntity<Page<ClinicaDTO>> listarClinicas(Pageable pageable){
        return ResponseEntity.ok(clinicaService.listarClinicas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> buscarClinicaPorId(@PathVariable Long id){
        return ResponseEntity.ok(clinicaService.buscarClinicaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaDTO> atualizarClinica(@PathVariable Long id, @RequestBody ClinicaDTO clinicaAtualizada){
        ClinicaDTO clinicaEncontrada = clinicaService.buscarClinicaPorId(id);
        clinicaEncontrada.setNome(clinicaAtualizada.getNome());
        clinicaEncontrada.setCidade(clinicaAtualizada.getCidade());
        clinicaEncontrada.setCustoMedioConsulta(clinicaAtualizada.getCustoMedioConsulta());
        return ResponseEntity.ok(clinicaService.criarClinica(clinicaEncontrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClinica(@PathVariable Long id){
        clinicaService.deletarClinica(id);
        return ResponseEntity.noContent().build();
    }
}
