package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "unidadeEdificacao_id"})})
public class Ambiente extends AbstractEntity {
    @NotEmpty(message = "Informe o nome do ambiente.")
    private String nome;
    private String descricao;
    private boolean ativo;

    @OneToMany(mappedBy = "ambiente")
    private List<Equipamento> equipamentos;

    @NotNull(message = "Informe uma Unidade de Edificação.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidadeEdificacao_id")
    private UnidadeEdificacao unidadeEdificacao;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public UnidadeEdificacao getUnidadeEdificacao() {
        return unidadeEdificacao;
    }

    public void setUnidadeEdificacao(UnidadeEdificacao unidadeEdificacao) {
        this.unidadeEdificacao = unidadeEdificacao;
    }


}
