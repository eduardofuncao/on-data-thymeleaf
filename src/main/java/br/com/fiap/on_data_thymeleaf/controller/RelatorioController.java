package br.com.fiap.on_data_thymeleaf.controller;

import br.com.fiap.on_data_thymeleaf.controller.dto.OcorrenciaDTO;
import br.com.fiap.on_data_thymeleaf.service.DentistaService;
import br.com.fiap.on_data_thymeleaf.service.DoencaService;
import br.com.fiap.on_data_thymeleaf.service.OcorrenciaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    private final OcorrenciaService ocorrenciaService;
    private final DentistaService dentistaService;
    private final DoencaService doencaService;

    public RelatorioController(OcorrenciaService ocorrenciaService, DentistaService dentistaService, DoencaService doencaService) {
        this.ocorrenciaService = ocorrenciaService;
        this.dentistaService = dentistaService;
        this.doencaService = doencaService;
    }

    @GetMapping
    public String relatorioOcorrencias(Model model) {
        List<OcorrenciaDTO> ocorrencias = ocorrenciaService.listarOcorrencias();
        List<OcorrenciaDTO> ocorrenciasAprovadas = ocorrenciaService.listarOcorrenciasAprovadas();
        List<OcorrenciaDTO> ocorrenciasReprovadas = ocorrenciaService.listarOcorrenciasReprovadas();
        Map<Integer, Double> valorPorMes = ocorrenciaService.mapearValorPorMes();

        model.addAttribute("totalOcorrencias", ocorrencias.size());
        model.addAttribute("totalAprovadas", ocorrenciasAprovadas.size());
        model.addAttribute("totalReprovadas", ocorrenciasReprovadas.size());
        model.addAttribute("valorPorMes", valorPorMes);

        DoubleSummaryStatistics valorStats = ocorrencias.stream()
                .map(OcorrenciaDTO::getValor)
                .collect(Collectors.summarizingDouble(Double::doubleValue));

        model.addAttribute("valorTotal", valorStats.getSum());
        model.addAttribute("valorMedio", valorStats.getAverage());
        model.addAttribute("valorMaximo", valorStats.getMax());
        model.addAttribute("valorMinimo", valorStats.getMin());

        DoubleSummaryStatistics duracaoStats = ocorrencias.stream()
                .mapToDouble(OcorrenciaDTO::getDuracaoHoras)
                .summaryStatistics();

        model.addAttribute("duracaoTotal", duracaoStats.getSum());
        model.addAttribute("duracaoMedia", duracaoStats.getAverage());
        model.addAttribute("duracaoMaxima", duracaoStats.getMax());
        model.addAttribute("duracaoMinima", duracaoStats.getMin());

        return "relatorio/painel";
    }
}
