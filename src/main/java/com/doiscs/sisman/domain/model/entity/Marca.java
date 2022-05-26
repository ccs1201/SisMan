package com.doiscs.sisman.domain.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Marca extends AbstractEntity {

    @Column(unique = true)
    @NotEmpty(message = "Informe o Nome da marca.")
    @Length(min = 2, max = 25, message = "Descrição da marca deve conter entre 2 e 25 caracteres.")
    private String nome;
    @OneToMany(mappedBy = "marca")
    private List<ModeloEquipamento> modeloEquipamentos;
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ModeloEquipamento> getModeloEquipamentos() {
        return modeloEquipamentos;
    }

    public void setModeloEquipamentos(List<ModeloEquipamento> modeloEquipamentos) {
        this.modeloEquipamentos = modeloEquipamentos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
