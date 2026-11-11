package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<Optional<OrdemServico>> cadastrarOrdemServico(@Valid @RequestBody OrdemServicoRequest ordemServicoRequest) {
        Optional<OrdemServico> ordemServicoCadastrada = ordemServicoService.cadastrarOrdemServico(ordemServicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarOrdensServico() {
        List<OrdemServico> ordensServico = ordemServicoService.listarOrdensServico();
        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemServicoPorId(@PathVariable Integer id) {
        Optional<OrdemServico> ordemServicoEncontrada = ordemServicoService.buscarOrdemServicoPorId(id);

        if (ordemServicoEncontrada.isPresent()) {
            return ResponseEntity.ok(ordemServicoEncontrada.get());
        }

        return ResponseEntity.notFound().build();
    }
}
