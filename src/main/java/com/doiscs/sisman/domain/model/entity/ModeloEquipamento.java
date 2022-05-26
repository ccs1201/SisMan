package com.doiscs.sisman.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UN_ModeloEquipamento_marca_modelo_tipo", columnNames = {
        "marca_id", "nome", "tipoequipamento_id"})})
public class ModeloEquipamento extends AbstractEntity {

    private boolean ativo;

    @NotNull(message = "Informe a Marca")
    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    @NotEmpty(message = "Informe o Modelo")
    private String nome;

    @NotNull(message = "Informe o tipo do Equipamento")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoequipamento_id")
    private TipoEquipamento tipoEquipamento;

    private String obs;

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

}
