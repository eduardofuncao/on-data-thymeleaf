package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.controller.dto.PacienteDTO;
import br.com.fiap.on_data_thymeleaf.entity.Paciente;
import br.com.fiap.on_data_thymeleaf.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "paciente/lista";
    }

    @GetMapping("/novo")
    public String novoPaciente(Model model) {
        model.addAttribute("paciente", new PacienteDTO());
        return "paciente/form";
    }

    @PostMapping
    public String salvarPaciente(@Valid PacienteDTO pacienteDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) return "paciente/form";

        pacienteService.criarPaciente(pacienteDTO);
        attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.buscarPacientePorId(id));
        return "paciente/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id, RedirectAttributes attributes) {
        pacienteService.deletarPaciente(id);
        attributes.addFlashAttribute("mensagem", "Paciente removido com sucesso");
        return "redirect:/pacientes";
    }

}
