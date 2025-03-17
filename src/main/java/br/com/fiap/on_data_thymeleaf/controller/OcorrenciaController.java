package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.controller.dto.OcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.service.DentistaService;
import br.com.fiap.on_data_thymeleaf.service.DoencaService;
import br.com.fiap.on_data_thymeleaf.service.OcorrenciaService;
import br.com.fiap.on_data_thymeleaf.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;
    private final PacienteService pacienteService;
    private final DentistaService dentistaService;
    private final DoencaService doencaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService, PacienteService pacienteService, DentistaService dentistaService, DoencaService doencaService) {
        this.ocorrenciaService = ocorrenciaService;
        this.pacienteService = pacienteService;
        this.dentistaService = dentistaService;
        this.doencaService = doencaService;
    }

    @GetMapping
    public String listarOcorrencias(Model model) {
        model.addAttribute("ocorrencias", ocorrenciaService.listarOcorrencias());
        return "ocorrencia/lista";
    }

    @GetMapping("/novo")
    public String novaOcorrencia(Model model) {
        model.addAttribute("ocorrenciaDTO", new OcorrenciaDTO());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("dentistas", dentistaService.listarDentistas());
        model.addAttribute("doencas", doencaService.listarDoencas());
        return "ocorrencia/form";
    }

    @PostMapping
    public String salvarOcorrencia(@Valid OcorrenciaDTO ocorrenciaDTO, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteService.listarPacientes());
            model.addAttribute("dentistas", dentistaService.listarDentistas());
            model.addAttribute("doencas", doencaService.listarDoencas());
            return "ocorrencia/form";
        }

        ocorrenciaService.criarOcorrencia(ocorrenciaDTO);
        attributes.addFlashAttribute("mensagem", "Ocorrência registrada com sucesso!");
        return "redirect:/ocorrencias";
    }

    @GetMapping("/editar/{id}")
    public String editarOcorrencia(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ocorrenciaDTO", ocorrenciaService.buscarOcorrenciaPorId(id));
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("dentistas", dentistaService.listarDentistas());
        model.addAttribute("doencas", doencaService.listarDoencas());
        return "ocorrencia/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirOcorrencia(@PathVariable("id") Long id, RedirectAttributes attributes) {
        ocorrenciaService.deletarOcorrencia(id);
        attributes.addFlashAttribute("mensagem", "Ocorrência excluída com sucesso!");
        return "redirect:/ocorrencias";
    }

    @GetMapping("/aprovar/{id}")
    public String aprovarOcorrencia(@PathVariable("id") Long id, RedirectAttributes attributes) {
        ocorrenciaService.aprovarOcorrencia(id);
        attributes.addFlashAttribute("mensagem", "Ocorrência aprovada com sucesso!");
        return "redirect:/ocorrencias";
    }
}
