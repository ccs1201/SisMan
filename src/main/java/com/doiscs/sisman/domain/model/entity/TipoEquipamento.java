package com.doiscs.sisman.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class TipoEquipamento extends AbstractEntity {

    @NotEmpty(message = "Informe a Descrição para o tipo de equipamento.")
    @Column(unique = true)
    private String nome;
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String descricao) {
        this.nome = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
