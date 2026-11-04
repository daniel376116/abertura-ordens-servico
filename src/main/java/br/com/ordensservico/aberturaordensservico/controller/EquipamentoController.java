package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Optional<Equipamento>> cadastrarEquipamento(@Valid @RequestBody EquipamentoRequest equipamento) {
        Optional<Equipamento> equipamentoCadastrado = equipamentoService.cadastrarEquipamento(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        List<Equipamento> equipamentos = equipamentoService.listarEquipamentos();
        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarEquipamentoPorId(@PathVariable Integer id) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoService.buscarEquipamentoPorId(id);

        if (equipamentoEncontrado.isPresent()) {
            return ResponseEntity.ok(equipamentoEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizarEquipamento(@PathVariable Integer id, @Valid @RequestBody EquipamentoRequest equipamentoRequest) {
        Optional<Equipamento> equipamentoAtual = equipamentoService.atualizarEquipamento(id, equipamentoRequest);

        if (equipamentoAtual.isPresent()) {
            return ResponseEntity.ok(equipamentoAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEquipamento(@PathVariable Integer id) {
        boolean excluido = equipamentoService.excluirEquipamento(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
