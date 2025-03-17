package br.com.fiap.on_data_thymeleaf.controller.api;

import br.com.fiap.on_data_thymeleaf.controller.dto.DoencaDTO;
import br.com.fiap.on_data_thymeleaf.repository.DoencaRepository;
import br.com.fiap.on_data_thymeleaf.service.DoencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/doencas")
public class DoencaAPIController {
    @Autowired
    private DoencaService doencaService;
    @Autowired
    private DoencaRepository doencaRepository;

    @PostMapping
    ResponseEntity<DoencaDTO> criarDoenca(@RequestBody DoencaDTO doencaDTO){
        return ResponseEntity.ok(doencaService.criarDoenca(doencaDTO));
    }

    @GetMapping
    ResponseEntity<Page<DoencaDTO>> listarDoencas(Pageable pageable){
        return ResponseEntity.ok(doencaService.listarDoencas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoencaDTO> buscarDoencaPorId(@PathVariable Long id){
        return ResponseEntity.ok(doencaService.buscarDoencaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoencaDTO> atualizarDoenca(@PathVariable Long id, @RequestBody DoencaDTO doencaAtualizada){
        DoencaDTO doencaEncontrada = doencaService.buscarDoencaPorId(id);
        doencaEncontrada.setNome(doencaAtualizada.getNome());
        doencaEncontrada.setTaxaReincidencia(doencaAtualizada.getTaxaReincidencia());
        doencaEncontrada.setGravidade(doencaAtualizada.getGravidade());
        return ResponseEntity.ok(doencaService.criarDoenca(doencaEncontrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoenca(@PathVariable Long id){
        doencaService.deletarDoenca(id);
        return ResponseEntity.noContent().build();
    }
}
