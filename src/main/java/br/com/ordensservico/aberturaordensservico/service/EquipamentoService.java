package br.com.ordensservico.aberturaordensservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service; 
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<Equipamento> cadastrarEquipamento(Equipamento equipamento) {
        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public List<Equipamento> listarEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarEquipamentoPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizarEquipamento(Integer id, Equipamento equipamentoAtualizado) {
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();
        equipamento.setNome(equipamentoAtualizado.getNome());
        equipamento.setNumeroPatrimonio(equipamentoAtualizado.getNumeroPatrimonio());
        equipamento.setSetor(equipamentoAtualizado.getSetor());

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
