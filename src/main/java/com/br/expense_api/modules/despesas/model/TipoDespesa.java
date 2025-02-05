package com.br.expense_api.modules.despesas.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TipoDespesa {

    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    SAUDE("Saúde"),
    EDUCACAO("Educação"),
    MORADIA("Moradia"),
    LAZER("Lazer");

    private final String descricao;

    TipoDespesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para buscar o Enum por descrição
    public static TipoDespesa fromDescricao(String descricao) {
        for (TipoDespesa tipo : TipoDespesa.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de despesa inválido: " + descricao);
    }

    // Método para retornar todos os tipos como uma lista
    public static List<String> getTiposDescricao() {
        return Arrays.stream(TipoDespesa.values())
                .map(TipoDespesa::getDescricao)
                .collect(Collectors.toList());
    }
}

