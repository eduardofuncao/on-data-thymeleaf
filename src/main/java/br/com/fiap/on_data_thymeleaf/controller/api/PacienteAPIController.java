package br.com.fiap.on_data_thymeleaf.controller.api;

import br.com.fiap.on_data_thymeleaf.controller.dto.PacienteDTO;
import br.com.fiap.on_data_thymeleaf.repository.PacienteRepository;
import br.com.fiap.on_data_thymeleaf.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pacientes")
public class PacienteAPIController {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    ResponseEntity<PacienteDTO> criarPaciente(@RequestBody PacienteDTO pacienteDTO){
        return ResponseEntity.ok(pacienteService.criarPaciente(pacienteDTO));
    }

    @GetMapping
    ResponseEntity<Page<PacienteDTO>> listarPacientes(Pageable pageable){
        return ResponseEntity.ok(pacienteService.listarPacientes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPacientePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteAtualizada){
        PacienteDTO pacienteEncontrada = pacienteService.buscarPacientePorId(id);

        if (pacienteEncontrada == null) {
            return ResponseEntity.notFound().build();
        }

        pacienteEncontrada.setNome(pacienteAtualizada.getNome());
        pacienteEncontrada.setGenero(pacienteAtualizada.getGenero());
        pacienteEncontrada.setIdade(pacienteAtualizada.getIdade());
        pacienteEncontrada.setEndereco(pacienteAtualizada.getEndereco());
        pacienteEncontrada.setCidade(pacienteAtualizada.getCidade());
        pacienteEncontrada.setFumante(pacienteAtualizada.isFumante());
        pacienteEncontrada.setRenda(pacienteAtualizada.getRenda());
        pacienteEncontrada.setVisitasPorAno(pacienteAtualizada.getVisitasPorAno());
        pacienteEncontrada.setCategoria(pacienteAtualizada.getCategoria());
        return ResponseEntity.ok(pacienteService.criarPaciente(pacienteEncontrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id){
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
