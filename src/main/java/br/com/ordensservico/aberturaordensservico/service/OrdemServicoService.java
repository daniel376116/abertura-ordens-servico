package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.ordensservico.aberturaordensservico.dto.EncerramentoOrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;

@Service 
public class OrdemServicoService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, EquipamentoRepository equipamentoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public Optional<OrdemServico> cadastrarOrdemServico (OrdemServicoRequest ordemServicoRequest) {
        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setDescricao(ordemServicoRequest.getDescricao());
        
        Optional<Equipamento> equipamentoExists = equipamentoRepository.findById(ordemServicoRequest.getEquipamentoId());
        if (equipamentoExists.isEmpty()) {
            return Optional.empty();
        }
        ordemServico.setEquipamento(equipamentoExists.get());
        ordemServico.setDataAbertura(LocalDateTime.now());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarOrdemServicoPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }

    public Optional<OrdemServico> encerrarOrdemServico (Integer id, EncerramentoOrdemServicoRequest dados) {

        Optional<OrdemServico> ordemServicoExists = ordemServicoRepository.findById(id);
        
        if (ordemServicoExists.isEmpty()) {
            return Optional.empty();
        }

        if (ordemServicoExists.get().getDataEncerramento() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A ordem de serviço já foi encerrada");
        }

        OrdemServico ordemServico = ordemServicoExists.get();
        ordemServico.setDataEncerramento(LocalDateTime.now());
        ordemServico.setSolucao(dados.getSolucao());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }

    public List<OrdemServico> listarOrdensServicoPendentes() {
        return ordemServicoRepository.findByDataEncerramentoIsNull();
    }

}
