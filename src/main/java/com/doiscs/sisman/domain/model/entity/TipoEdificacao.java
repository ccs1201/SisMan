package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class TipoEdificacao extends AbstractEntity {

    @NotEmpty(message = "Informe um nome para o Tipo de Edificação.")
    @Column(unique = true)
    private String nome;
    private String descricao;
    private boolean ativo = true;
    @OneToMany(mappedBy = "tipoEdificacao")
    private List<Edificacao> edificacoes;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEdificacoes(List<Edificacao> edificacoes) {
        this.edificacoes = edificacoes;
    }

    public List<Edificacao> getEdificacoes() {

        return edificacoes;
    }

}
