package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
