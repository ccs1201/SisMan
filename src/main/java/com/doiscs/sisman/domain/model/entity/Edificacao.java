package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Edificacao extends AbstractEntity {

    @NotEmpty(message = "Informe no nome da edificação.")
    @Column(unique = true)
    private String nome;
    private String descricao;
    private String localizacao;

    @NotNull(message = "Informe o tipo da edificação.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoedificacao_id")
    private TipoEdificacao tipoEdificacao;

    @OneToMany(mappedBy = "edificacao")
    private List<UnidadeEdificacao> unidades;

    private boolean ativo = true;

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

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TipoEdificacao getTipoEdificacao() {
        return tipoEdificacao;
    }

    public void setTipoEdificacao(TipoEdificacao tipoEdificacao) {
        this.tipoEdificacao = tipoEdificacao;
    }

    public List<UnidadeEdificacao> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadeEdificacao> unidades) {
        this.unidades = unidades;
    }

}
