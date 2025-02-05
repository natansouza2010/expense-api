package com.br.expense_api.modules.despesas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private TipoDespesa tipo;
    private LocalDate data;


}
