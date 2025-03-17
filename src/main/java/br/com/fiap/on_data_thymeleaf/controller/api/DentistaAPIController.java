package br.com.fiap.on_data_thymeleaf.controller.api;

import br.com.fiap.on_data_thymeleaf.controller.dto.DentistaDTO;
import br.com.fiap.on_data_thymeleaf.repository.DentistaRepository;
import br.com.fiap.on_data_thymeleaf.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dentistas")
public class DentistaAPIController {
    @Autowired
    private DentistaService dentistaService;
    @Autowired
    private DentistaRepository dentistaRepository;

    @PostMapping
    ResponseEntity<DentistaDTO> criarDentista(@RequestBody DentistaDTO dentistaDTO){
        return ResponseEntity.ok(dentistaService.criarDentista(dentistaDTO));
    }

    @GetMapping
    ResponseEntity<Page<DentistaDTO>> listarDentistas(Pageable pageable){
        return ResponseEntity.ok(dentistaService.listarDentistas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarDentistaPorId(@PathVariable Long id){
        return ResponseEntity.ok(dentistaService.buscarDentistaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaDTO> atualizarDentista(@PathVariable Long id, @RequestBody DentistaDTO dentistaAtualizada){
        DentistaDTO dentistaEncontrada = dentistaService.buscarDentistaPorId(id);
        dentistaEncontrada.setNome(dentistaAtualizada.getNome());
        dentistaEncontrada.setIdade(dentistaAtualizada.getIdade());
        dentistaEncontrada.setRegistro(dentistaAtualizada.getRegistro());
        dentistaEncontrada.setSalario(dentistaAtualizada.getSalario());
        dentistaEncontrada.setClinicaId(dentistaAtualizada.getClinicaId());
        return ResponseEntity.ok(dentistaService.criarDentista(dentistaEncontrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDentista(@PathVariable Long id){
        dentistaService.deletarDentista(id);
        return ResponseEntity.noContent().build();
    }
}
