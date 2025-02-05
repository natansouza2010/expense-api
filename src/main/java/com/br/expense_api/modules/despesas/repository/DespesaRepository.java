package com.br.expense_api.modules.despesas.repository;

import com.br.expense_api.modules.despesas.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
