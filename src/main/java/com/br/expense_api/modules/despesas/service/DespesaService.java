package com.br.expense_api.modules.despesas.service;


import com.br.expense_api.config.exception.DespesaAlreadyExistsException;
import com.br.expense_api.config.exception.DespesaNotFoundException;
import com.br.expense_api.config.exception.InvalidDespesaException;
import com.br.expense_api.modules.despesas.model.Despesa;
import com.br.expense_api.modules.despesas.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public Despesa salvarDespesa(Despesa despesa) {
        validarDespesa(despesa);
        return despesaRepository.save(despesa);
    }


    public List<Despesa> listarDespesas() {
        return despesaRepository.findAll();
    }


    public Despesa encontrarDespesaPorId(Long id) {
        return despesaRepository.findById(id)
                .orElseThrow(() -> new DespesaNotFoundException("Despesa inexistente"));
    }


    public Despesa atualizarDespesa(Long id, Despesa despesaAtualizada) {
        validarDespesa(despesaAtualizada);

        Despesa despesaExistente = encontrarDespesaPorId(id);
        despesaAtualizada.setId(despesaExistente.getId());

        return despesaRepository.save(despesaAtualizada);
    }

    // Deletar despesa por ID
    public void deletarDespesa(Long id) {
        Despesa despesa = encontrarDespesaPorId(id);
        despesaRepository.delete(despesa);
    }

    // Validações antes de salvar ou atualizar
    private void validarDespesa(Despesa despesa) {
        if (despesa.getDescricao() == null || despesa.getDescricao().trim().isEmpty()) {
            throw new InvalidDespesaException("A descrição da despesa não pode estar vazia.");
        }
        if (despesa.getValor() == null || despesa.getValor() <= 0) {
            throw new InvalidDespesaException("O valor da despesa deve ser maior que zero.");
        }
    }
}

