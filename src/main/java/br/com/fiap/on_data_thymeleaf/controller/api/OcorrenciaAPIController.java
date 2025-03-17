package br.com.fiap.on_data_thymeleaf.controller.api;

import br.com.fiap.on_data_thymeleaf.controller.dto.OcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/ocorrencias")
public class OcorrenciaAPIController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @PostMapping
    ResponseEntity<OcorrenciaDTO> criarOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO){
        OcorrenciaDTO createdOcorrencia = ocorrenciaService.criarOcorrencia((ocorrenciaDTO));

        createdOcorrencia.add(linkTo(methodOn(OcorrenciaAPIController.class).listarOcorrencias()).withRel("ocorrencias"));
        createdOcorrencia.add(linkTo(methodOn(OcorrenciaAPIController.class).listarOcorrenciasReprovadas()).withRel("reprovadas"));
        createdOcorrencia.add(linkTo(methodOn(OcorrenciaAPIController.class).aprovarOcorrencia(createdOcorrencia.getId())).withRel("aprovar"));
        createdOcorrencia.add(linkTo(methodOn(OcorrenciaAPIController.class).deletarOcorrencia(createdOcorrencia.getId())).withRel("deletar"));

        createdOcorrencia.add(linkTo(methodOn(PacienteAPIController.class).buscarPacientePorId(createdOcorrencia.getPacienteId())).withRel("paciente"));

        return ResponseEntity.ok(createdOcorrencia);
    }

    @GetMapping
    ResponseEntity<CollectionModel<OcorrenciaDTO>> listarOcorrencias(){
        List<OcorrenciaDTO> ocorrencias = ocorrenciaService.listarOcorrencias();

        CollectionModel<OcorrenciaDTO> collectionModel = CollectionModel.of(ocorrencias);

        collectionModel.add(linkTo(methodOn(OcorrenciaAPIController.class).listarOcorrenciasAprovadas()).withRel("aprovadas"));
        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> buscarOcorrenciaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ocorrenciaService.buscarOcorrenciaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> atualizarOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaDTO ocorrenciaAtualizada){
        OcorrenciaDTO ocorrenciaEncontrada = ocorrenciaService.buscarOcorrenciaPorId(id);
        ocorrenciaEncontrada.setCodigoOcorrencia(ocorrenciaAtualizada.getCodigoOcorrencia());
        ocorrenciaEncontrada.setData(ocorrenciaAtualizada.getData());
        ocorrenciaEncontrada.setValor(ocorrenciaAtualizada.getValor());
        ocorrenciaEncontrada.setDuracaoHoras(ocorrenciaAtualizada.getDuracaoHoras());
        ocorrenciaEncontrada.setDoencaId(ocorrenciaAtualizada.getDoencaId());
        ocorrenciaEncontrada.setDentistaId(ocorrenciaAtualizada.getDentistaId());
        ocorrenciaEncontrada.setPacienteId(ocorrenciaAtualizada.getPacienteId());
        return ResponseEntity.ok(ocorrenciaService.criarOcorrencia(ocorrenciaEncontrada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOcorrencia(@PathVariable Long id){
        ocorrenciaService.deletarOcorrencia(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<OcorrenciaDTO> aprovarOcorrencia(@PathVariable Long id){
        OcorrenciaDTO ocorrenciaAprovada = ocorrenciaService.aprovarOcorrencia(id);
        ocorrenciaAprovada.add(linkTo(methodOn(OcorrenciaAPIController.class).listarOcorrenciasAprovadas()).withRel("aprovadas"));
        return ResponseEntity.ok(ocorrenciaAprovada);
    }

    @GetMapping("/aprovadas")
    public ResponseEntity<List<OcorrenciaDTO>> listarOcorrenciasAprovadas(){
        return ResponseEntity.ok(ocorrenciaService.listarOcorrenciasAprovadas());
    }

    @GetMapping("/reprovadas")
    public ResponseEntity<List<OcorrenciaDTO>> listarOcorrenciasReprovadas(){
        List<OcorrenciaDTO> ocorrenciasDTO = ocorrenciaService.listarOcorrenciasReprovadas();

        ocorrenciasDTO.forEach(ocorrenciaDTO -> {
            ocorrenciaDTO.add(linkTo(methodOn(OcorrenciaAPIController.class).aprovarOcorrencia(ocorrenciaDTO.getId())).withRel("aprovar"));
        });

        return ResponseEntity.ok(ocorrenciasDTO);
    }

}
