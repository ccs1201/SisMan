package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "edificacao_id"})})
public class UnidadeEdificacao extends AbstractEntity {

    @NotEmpty(message = "Informe o nome da Unidade")
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "unidadeEdificacao")
    List<Ambiente> ambientes;

    private boolean ativo;
    @NotNull(message = "Informe a edificação.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificacao_id")
    private Edificacao edificacao;

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

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Edificacao getEdificacao() {
        return edificacao;
    }

    public void setEdificacao(Edificacao edificacao) {
        this.edificacao = edificacao;
    }

}
