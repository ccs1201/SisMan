package com.doiscs.sisman.domain.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "ambiente_id", "ativo"})})
public class Equipamento extends AbstractEntity {

    private boolean ativo;

    @NotEmpty(message = "Informe Nome do Equipamento.")
    private String nome;

    @NotNull(message = "Informe o Tipo do Equipamento.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoequipamento_id")
    private TipoEquipamento tipoEquipamento;

    @NotNull(message = "Informe a Marca do Equipamento.")
    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToOne
    @JoinColumn(name = "modeloequipamento_id")
    private ModeloEquipamento modeloEquipamento;

    @NotNull(message = "Informe o ambiente do Equipamento.")
    @ManyToOne
    @JoinColumn(name = "ambiente_id")
    private Ambiente ambiente;

    //@NotNull(message = "Informe o Fornecedor do Equipamento")
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private LocalDate dataCompra;
    private LocalDate garantiaAte;
    private String descricao;
    private String nSerie;
    private String nPatrimonio;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public LocalDate getDatacompra() {
        return dataCompra;
    }

    public void setDatacompra(LocalDate datacompra) {
        this.dataCompra = datacompra;
    }

    public LocalDate getGarantiaAte() {
        return garantiaAte;
    }

    public void setGarantiaAte(LocalDate garantiaAte) {
        this.garantiaAte = garantiaAte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getnSerie() {
        return nSerie;
    }

    public void setnSerie(String nSerie) {
        this.nSerie = nSerie;
    }

    public String getnPatrimonio() {
        return nPatrimonio;
    }

    public void setnPatrimonio(String nPatrimonio) {
        this.nPatrimonio = nPatrimonio;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ModeloEquipamento getModeloEquipamento() {
        return modeloEquipamento;
    }

    public void setModeloEquipamento(ModeloEquipamento modeloEquipamento) {
        this.modeloEquipamento = modeloEquipamento;
    }

}
