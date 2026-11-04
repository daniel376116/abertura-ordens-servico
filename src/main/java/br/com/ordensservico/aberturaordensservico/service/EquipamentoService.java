package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.Setor;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.SetorRepository;

@Service
public class EquipamentoService {
    private final SetorRepository setorRepository;
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, SetorRepository setorRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }
    
    public Optional<Equipamento> cadastrarEquipamento(EquipamentoRequest equipamentoRequest) {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(equipamentoRequest.getNome());
        equipamento.setNumeroPatrimonio(equipamentoRequest.getNumeroPatrimonio());
        // Assuming you have a method to fetch the Setor by ID
        // equipamento.setSetor(setorService.buscarSetorPorId(equipamentoRequest.getSetorId()));
        Optional<Setor> setorExists = setorRepository.findById(equipamentoRequest.getSetorId());
        if (setorExists.isEmpty()) {
            return Optional.empty();
        }
        equipamento.setSetor(setorExists.get());
        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarEquipamentoPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizarEquipamento(Integer id, EquipamentoRequest equipamentoRequest) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

         Optional<Setor> setorExists = setorRepository.findById(equipamentoRequest.getSetorId());
        if (setorExists.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();
        equipamento.setNome(equipamentoRequest.getNome());
        equipamento.setNumeroPatrimonio(equipamentoRequest.getNumeroPatrimonio());
        equipamento.setSetor(setorExists.get());

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluirEquipamento(Integer id) {
        if (!equipamentoRepository.existsById(id)) {
            return false;
        }
        
        equipamentoRepository.deleteById(id);
        return true;
    }
}
