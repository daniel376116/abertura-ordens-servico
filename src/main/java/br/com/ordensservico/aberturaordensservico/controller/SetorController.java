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

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.service.SetorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/setores")
public class SetorController {
    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<Setor> cadastrarSetor(@Valid @RequestBody Setor setor) {
        Setor setorCadastrado = setorService.cadastrarSetor(setor);

        return ResponseEntity.status(HttpStatus.CREATED).body(setorCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Setor>> listarSetores() {
        List<Setor> setores = setorService.listarSetores();

        return ResponseEntity.ok(setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarSEtorPorId(@PathVariable Integer id) {
        Optional<Setor> setorEncontrado = setorService.buscarSetorPorId(id);

        if (setorEncontrado.isPresent()) {
            return ResponseEntity.ok(setorEncontrado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Setor> atualizarSetor(@Valid @RequestBody @PathVariable Integer id,Setor setorAtualizado) {
        Optional<Setor> setorAtual = setorService.atualizarSetor(id, setorAtualizado);

        if (setorAtual.isPresent()) {
            return ResponseEntity.ok(setorAtual.get());
        } 
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluirSetor(@PathVariable Integer id) {
        boolean excluido = setorService.excluirSetor(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    
}
