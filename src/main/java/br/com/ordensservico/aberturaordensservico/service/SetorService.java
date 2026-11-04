package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service 
public class SetorService {
    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public Setor cadastrarSetor(Setor setor) {
        return setorRepository.save(setor);
    }

    public List<Setor> listarSetores() {
        return setorRepository.findAll();
    }

    public Optional<Setor> buscarSetorPorId(Integer id) {
        return setorRepository.findById(id);
    }

    public Optional<Setor> atualizarSetor(Integer id, Setor setorAtualizado) {
        Optional<Setor> setorEncontrado = setorRepository.findById(id);

        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Setor setor = setorEncontrado.get();

        setor.setNome(setorAtualizado.getNome());

        return Optional.of(setorRepository.save(setor));
        
    }

    public boolean excluirSetor(Integer id) {
       
        if (!setorRepository.existsById(id)) {
            return false;
        }

        setorRepository.deleteById(id);
        return true;
        
    }
}
