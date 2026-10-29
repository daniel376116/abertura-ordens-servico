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

    public Optional<Setor> listarSetoresPorId(Integer id) {
        return setorRepository.findById(id);
    }
}
